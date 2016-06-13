package com.worksap.stm2016.repository;


import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.workforce.Workforce;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public interface WorkforceRepository extends CrudRepository<Workforce, Long> {
    public List<Workforce> findByDepartmentAndYearOrderByMonthAsc(Department department, Short year);
}
