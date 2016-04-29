package com.worksap.stm2016.repository;


import com.worksap.stm2016.domain.Job;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends PagingAndSortingRepository<Job, Long>,
        JpaSpecificationExecutor {
}
