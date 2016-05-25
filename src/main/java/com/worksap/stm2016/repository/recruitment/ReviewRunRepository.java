package com.worksap.stm2016.repository.recruitment;


import com.worksap.stm2016.domain.review.ReviewRun;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRunRepository extends PagingAndSortingRepository<ReviewRun, Long>,
        JpaSpecificationExecutor {
}
