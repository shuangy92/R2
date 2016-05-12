package com.worksap.stm2016.repository.job;


import com.worksap.stm2016.domain.job.JobCategory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends PagingAndSortingRepository<JobCategory, Long>,
        JpaSpecificationExecutor {

    public Iterable<JobCategory> findAllByOrderByNameAsc();
    public JobCategory findOneByName(String name);
}
