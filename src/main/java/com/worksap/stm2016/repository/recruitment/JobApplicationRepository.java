package com.worksap.stm2016.repository.recruitment;


import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.domain.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JobApplicationRepository extends PagingAndSortingRepository<JobApplication, Long>,
        JpaSpecificationExecutor {

    JobApplication findOneByJobPostAndApplicant(JobPost jobPost, User applicant);
    Collection<JobApplication> findByApplicant(User applicant);
}
