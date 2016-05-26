package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.review.ReviewFlow;
import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.ReviewStatus;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.message.NotificationRepository;
import com.worksap.stm2016.repository.recruitment.JobApplicationRepository;
import com.worksap.stm2016.repository.recruitment.ReviewFlowRepository;
import com.worksap.stm2016.repository.recruitment.ReviewResponseRepository;
import com.worksap.stm2016.service.message.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewResponseService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewResponseService.class);
    @Autowired
    ReviewResponseRepository reviewResponseRepository;
    @Autowired
    ReviewFlowRepository reviewFlowRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    JobApplicationRepository jobApplicationRepository;

    public ReviewResponse get(Long id){
        return reviewResponseRepository.findOne(id);
    }

    public ReviewResponse save(ReviewResponse reviewResponse){
        return reviewResponseRepository.save(reviewResponse);
    }

    public ReviewResponse updateResponseOfJobApplication(Long jobApplicationId, ReviewResponse reviewResponse){
        boolean finishRun = true;
        JobApplication jobApplication = jobApplicationRepository.findOne(jobApplicationId);
        for (ReviewResponse response: jobApplication.getResponses()) {
            if (response.getReviewRun() == reviewResponse.getReviewRun()) {
                if (response.getStatus() == ReviewStatus.REVIEWING) {
                    finishRun = false;
                }
            }
        }
        /*Review notification for HR*/
        if (finishRun) {
            notificationService.createReviewNotification(jobApplication, reviewResponse, Notification.NotificationType.REVIEW_UPDATE_HR);

            /*Review notification for reviewers*/
            for (ReviewResponse response: jobApplication.getResponses()) {
                if (response.getReviewRun().getRunNumber() == reviewResponse.getReviewRun().getRunNumber() + 1) {
                    notificationService.createReviewNotification(jobApplication, response, Notification.NotificationType.REVIEW_UPDATE);
                }
            }
        }
        reviewResponse.setJobApplication(jobApplication);
        return reviewResponseRepository.save(reviewResponse);
    }

    public void delete(Long id){
        reviewResponseRepository.delete(id);
    }

    public Long deleteList(@RequestBody ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                reviewResponseRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }

    public JobApplication createResponseList(JobApplication jobApplication){
        ReviewFlow reviewFlow = reviewFlowRepository.findOne(jobApplication.getJobPost().getReviewFlow().getId());
        for (ReviewRun reviewRun: reviewFlow.getRuns()) {
            for (User reviewer: reviewRun.getReviewers()) {
                ReviewResponse response = new ReviewResponse();
                response.setJobApplication(jobApplication);
                response.setReviewRun(reviewRun);
                response.setReviewer(reviewer);
                response = reviewResponseRepository.save(response);
                jobApplication.addResponse(response);

                /* Review notification for reviewers */
                notificationService.createReviewNotification(jobApplication, response, Notification.NotificationType.REVIEW_START);
            }
        }
        return jobApplicationRepository.save(jobApplication);
    }
}
