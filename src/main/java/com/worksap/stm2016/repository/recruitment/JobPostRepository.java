package com.worksap.stm2016.repository.recruitment;


import com.worksap.stm2016.domain.recruitment.JobPost;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface JobPostRepository extends PagingAndSortingRepository<JobPost, Long>,
        JpaSpecificationExecutor {
    Collection<JobPost> findByDeadline(Date deadline);
}
