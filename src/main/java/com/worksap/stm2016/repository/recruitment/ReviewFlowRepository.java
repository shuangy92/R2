package com.worksap.stm2016.repository.recruitment;


import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.domain.review.ReviewFlow;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewFlowRepository extends PagingAndSortingRepository<ReviewFlow, Long>,
        JpaSpecificationExecutor {
}
