package com.worksap.stm2016.repository.job;


import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.job.Department;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>,
        JpaSpecificationExecutor {
    Collection<Contract> findByEndDate(Date endDate);
    Collection<Contract> findByEndDateBetweenAndActive(Date from, Date to, Boolean active);
}
