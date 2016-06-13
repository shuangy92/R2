package com.worksap.stm2016.api;

import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.workforce.Workforce;
import com.worksap.stm2016.repository.WorkforceRepository;
import com.worksap.stm2016.repository.job.ContractRepository;
import com.worksap.stm2016.repository.job.DepartmentRepository;
import com.worksap.stm2016.repository.job.JobHistoryRepository;
import com.worksap.stm2016.util.DateUtil;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.worksap.stm2016.specification.BasicSpecs.*;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/workforce")
public class WorkforceApi {

    private static final Logger logger = LoggerFactory.getLogger(WorkforceApi.class);
    @Autowired
    WorkforceRepository workforceRepository;
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    JobHistoryRepository jobHistoryRepository;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Long> getData(@RequestParam(name="departmentId") Long id,
                              @RequestParam(name="year") Short year) {
        Department department = new Department();
        department.setId(id);
        List<Workforce> workforces = workforceRepository.findByDepartmentAndYearOrderByMonthAsc(department, year);
        return workforces.stream().map(Workforce::getCount).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @RequestMapping(value = "average", method = RequestMethod.GET)
    public List<Long> getAverageData(@RequestParam(name="departmentId") Long id,
                                     @RequestParam(name="from") Short from,
                                     @RequestParam(name="to") Short to) {
        Department department = new Department();
        department.setId(id);
        List<Long> result = null;
        for (short i = from; i <= to; i++) {
            List<Workforce> workforces = workforceRepository.findByDepartmentAndYearOrderByMonthAsc(department, from);
            List<Long> temp = workforces.stream().map(Workforce::getCount).collect(Collectors.toList());
            if (result == null) {
                result = temp;
            } else {
                for (short j = 0; j < 12; j++) {
                    result.set(j, result.get(j) + temp.get(j));
                }
            }
        }
        return result;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @RequestMapping(value = "calculate", method = RequestMethod.POST)
    public void calculateWorkforce() {
        Date lastDate = contractRepository.findFirstByOrderByEndDateDesc().get(0).getEndDate();

        LocalDate start = new LocalDate().withDayOfMonth(1);
        LocalDate end = new LocalDate().plusMonths(1).withDayOfMonth(1).minusDays(1);
        do {
            Iterable<Department> departmentList = departmentRepository.findAll();
            for (Department department : departmentList) {
                Specifications spec = where(betweenDates("endDate", start.toDate(), end.toDate()));
                spec = spec.or(betweenDates("startDate", start.toDate(), end.toDate()));
                spec = spec.or(where(ltDate("startDate", start.toDate())).and(gtDate("endDate", end.toDate())));
                spec = spec.and(isValue("job", "department", department));
                //List a = contractRepository.findAll(spec);
                //List b = contractRepository.findAll(spec);
                Long count = Long.valueOf(contractRepository.count(spec) + jobHistoryRepository.count(spec));
                Integer year = start.getYear();
                Integer month = start.getMonthOfYear();
                Workforce workforce = new Workforce(department, year.shortValue(), month.shortValue(), count);
                workforceRepository.save(workforce);
            }
            start = start.plusMonths(1);
            end = start.plusMonths(1).minusDays(1);
        } while (start.toDate().before(lastDate));
    }
}
