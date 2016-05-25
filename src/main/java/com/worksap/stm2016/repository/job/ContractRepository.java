package com.worksap.stm2016.repository.job;


import com.worksap.stm2016.domain.job.Contract;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>,
        JpaSpecificationExecutor {
}
