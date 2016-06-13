package com.worksap.stm2016;

import com.worksap.stm2016.domain.workforce.Workforce;
import com.worksap.stm2016.domain.job.JobHistory;
import com.worksap.stm2016.domain.Property;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.WorkforceRepository;
import com.worksap.stm2016.repository.job.DepartmentRepository;
import com.worksap.stm2016.repository.job.JobHistoryRepository;
import com.worksap.stm2016.repository.PropertyRepository;
import com.worksap.stm2016.repository.job.ContractRepository;
import com.worksap.stm2016.repository.recruitment.JobPostRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import com.worksap.stm2016.service.message.NotificationService;
import com.worksap.stm2016.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.worksap.stm2016.specification.BasicSpecs.*;
import static org.springframework.data.jpa.domain.Specifications.where;


/**
 * Created by Shuang on 5/25/2016.
 */
@Component
@RestController
public class ScheduledTasks {

    @Autowired
    ContractRepository contractRepository;
    @Autowired
    JobHistoryRepository jobHistoryRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    JobPostRepository jobPostRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    WorkforceRepository workforceRepository;

    @Scheduled(cron = "0 0 1 * * ?") // fire monthly
    @RequestMapping(value = "/checkExpiringContracts", method = RequestMethod.GET)
    public void checkExpiringContracts() {

        Date from = DateUtil.addDays(propertyRepository.findOne(Property.PropertyName.contractNotifyDaysBefore).getValue());
        Date to = DateUtil.addDays(from, propertyRepository.findOne(Property.PropertyName.contractNotifyDayLength).getValue());

        HashMap<Department, Integer> departmentExpContractsCount = new HashMap();

        Collection<Contract> contracts = contractRepository.findByEndDateBetween(from, to);
        for (Contract contract: contracts) {
            Department department = contract.getJob().getDepartment();
            if (department.getManager() != null) {
                if (departmentExpContractsCount.containsKey(department)) {
                    departmentExpContractsCount.put(department, departmentExpContractsCount.get(department) + 1);
                } else {
                    departmentExpContractsCount.put(department, 1);
                }
            }
        }
        for (HashMap.Entry<Department, Integer> entry : departmentExpContractsCount.entrySet()) {
            Department department = entry.getKey();
            Integer expiringCount = entry.getValue();
            notificationService.createContractExpiringNotification(department.getManager(), Long.valueOf(expiringCount), from, to);
        }
    }

    @Scheduled(cron = "0 1 1 * * ?") // fire daily
    @RequestMapping(value = "/checkExpiredContracts", method = RequestMethod.GET)
    public void checkExpiredContracts() {
        Collection<Contract> contracts = contractRepository.findByEndDate(DateUtil.addDays(-1));
        for (Contract contract : contracts) {
            notificationService.createContractExpiredNotification(contract);
            contract.getUser().setRole(Role.FORMER_EMPLOYEE);
            contract.getUser().setDepartment(null);
            /*if (propertyRepository.findOne(Property.PropertyName.autoAddFormerEmployee).getValue() == 1) {
                contract.getUser().setTalent(true);
            }*/
            userRepository.save(contract.getUser());

            jobHistoryRepository.save(new JobHistory(contract));
            contractRepository.delete(contract);
        }
    }

    @Scheduled(cron = "0 1 1 * * ?") // fire daily
    @RequestMapping(value = "/checkExpiredJobPosts", method = RequestMethod.GET)
    public void checkExpiredJobPosts() {
        Collection<JobPost> jobPosts = jobPostRepository.findByDeadline(DateUtil.addDays(-1));
        for (JobPost jobPost : jobPosts) {
            jobPost.setOpen(false);
            jobPostRepository.save(jobPost);
        }
    }

    @Scheduled(cron = "0 0 1 * * ?") // fire monthly
    @RequestMapping(value = "/calculateWorkforce", method = RequestMethod.GET)
    public void calculateWorkforceOfLastMonth() {
        LocalDate start = new LocalDate().plusMonths(-1).withDayOfMonth(1);
        LocalDate end = new LocalDate().withDayOfMonth(1).minusDays(1);

        Iterable<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            Specifications spec = where(betweenDates("endDate", start.toDate(), end.toDate()));
            spec = spec.or(betweenDates("startDate", start.toDate(), end.toDate()));
            spec = spec.or(where(ltDate("startDate", start.toDate())).and(gtDate("endDate", end.toDate())));
            spec = spec.and(isValue("job", "department", department));
            Long count = Long.valueOf(contractRepository.count(spec) + jobHistoryRepository.count(spec));
            Integer year = start.getYear();
            Integer month = start.getMonthOfYear();
            Workforce workforce = new Workforce(department, year.shortValue(), month.shortValue(), count);
            workforceRepository.save(workforce);
        }
    }
}