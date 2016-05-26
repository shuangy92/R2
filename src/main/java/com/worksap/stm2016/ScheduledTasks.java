package com.worksap.stm2016;

import com.worksap.stm2016.domain.Property;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.job.Job;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.repository.PropertyRepository;
import com.worksap.stm2016.repository.job.ContractRepository;
import com.worksap.stm2016.repository.job.DepartmentRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import com.worksap.stm2016.service.message.NotificationService;
import com.worksap.stm2016.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.worksap.stm2016.specification.BasicSpecs.betweenDates;
import static com.worksap.stm2016.specification.BasicSpecs.andFilterCount;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;

/**
 * Created by Shuang on 5/25/2016.
 */
@Component
//@RestController
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ContractRepository contractRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    NotificationService notificationService;

    @Scheduled(cron = "0 0 1 * * ?") // fire monthly
    //@RequestMapping(value = "/set", method = RequestMethod.GET)
    public void checkExpiringContracts() {

        Date from = DateUtil.addDays(propertyRepository.findOne(Property.PropertyName.contractNotifyDaysBefore).getValue());
        Date to = DateUtil.addDays(from, propertyRepository.findOne(Property.PropertyName.contractNotifyDayLength).getValue());

        HashMap<Department, Integer> departmentExpContractsCount = new HashMap();

        Collection<Contract> contracts = contractRepository.findByEndDateBetweenAndActive(from, to, true);
        for (Contract contract: contracts) {
            Department department = contract.getJob().getDepartment();
            if (department.getManagerId() != null) {
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
            notificationService.createContractNotification(department.getManagerId(), Long.valueOf(expiringCount), from, to, Notification.NotificationType.CONTRACT_EXPIRING);
        }
    }

    @Scheduled(cron = "0 1 1 * * ?") // fire daily
    public void setExpiredContracts() {
        Collection<Contract> contracts = contractRepository.findByEndDate(DateUtil.addDays(-1));
        for (Contract contract : contracts) {
            contract.setActive(false);
            contract.getUser().setActive(false);

            contractRepository.save(contract);
            userRepository.save(contract.getUser());
        }
    }


}