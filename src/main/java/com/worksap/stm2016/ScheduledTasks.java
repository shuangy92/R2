package com.worksap.stm2016;

import com.worksap.stm2016.domain.JobHistory;
import com.worksap.stm2016.domain.Property;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.repository.JobHistoryRepository;
import com.worksap.stm2016.repository.PropertyRepository;
import com.worksap.stm2016.repository.job.ContractRepository;
import com.worksap.stm2016.repository.recruitment.JobPostRepository;
import com.worksap.stm2016.service.message.NotificationService;
import com.worksap.stm2016.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;


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
            notificationService.createContractNotification(department.getManager(), Long.valueOf(expiringCount), from, to, Notification.NotificationType.CONTRACT_EXPIRING);
        }
    }

    @Scheduled(cron = "0 1 1 * * ?") // fire daily
    @RequestMapping(value = "/setExpiredContracts", method = RequestMethod.GET)
    public void setExpiredContracts() {
        Collection<Contract> contracts = contractRepository.findByEndDate(DateUtil.addDays(-1));
        for (Contract contract : contracts) {
            jobHistoryRepository.save(new JobHistory(contract));
            contractRepository.delete(contract);
        }
    }

    @Scheduled(cron = "0 1 1 * * ?") // fire daily
    @RequestMapping(value = "/setExpiredJobPosts", method = RequestMethod.GET)
    public void setExpiredJobPosts() {
        Collection<JobPost> jobPosts = jobPostRepository.findByDeadline(DateUtil.addDays(-1));
        for (JobPost jobPost : jobPosts) {
            jobPost.setOpen(false);
            jobPostRepository.save(jobPost);
        }
    }
}