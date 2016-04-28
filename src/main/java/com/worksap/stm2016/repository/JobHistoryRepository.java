package com.worksap.stm2016.repository;


import com.worksap.stm2016.domain.JobHistory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends PagingAndSortingRepository<JobHistory, Long>,
        JpaSpecificationExecutor {
}
