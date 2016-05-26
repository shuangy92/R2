package com.worksap.stm2016;

import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.repository.job.ContractRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import com.worksap.stm2016.service.job.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Shuang on 5/25/2016.
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ContractService contractService;
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = "0 1 1 * * ?")
    public void checkExpiringContracts() {
    }

    @Scheduled(cron = "0 1 1 * * ?")
    public void setExpiredContracts() {
        List<Contract> contracts = (List) contractRepository.findByEndDate(addDays(-1));
        for (Contract contract : contracts) {
            contract.setActive(false);
            contract.getUser().setActive(false);

            contractRepository.save(contract);
            userRepository.save(contract.getUser());
        }
    }

    private Date addDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}