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
    NotificationRepository notificationRepository;
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
            Notification notification = new Notification();
            notification.setContent("Review \"" + reviewResponse.getReviewRun().getTask() + "\" for application " + jobApplication.getId() + " has finished.");
            notification.setType(Notification.NotificationType.REVIEW_UPDATE_HR);
            notification.setItemId(jobApplication.getId());
            notification.setRole(Role.ADMIN);
            notificationRepository.save(notification);

            /*Review notification for reviewers*/
            for (ReviewResponse response: jobApplication.getResponses()) {
                if (response.getReviewRun().getRunNumber() == reviewResponse.getReviewRun().getRunNumber() + 1) {
                    notification = new Notification();
                    notification.setContent("It's your turn for review: " + response.getReviewRun().getTask());
                    notification.setType(Notification.NotificationType.REVIEW_UPDATE);
                    notification.setItemId(jobApplication.getId());
                    notification.setUser(response.getReviewer());
                    notificationRepository.save(notification);
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

    public JobApplication createResponseList(JobApplication application){
        ReviewFlow reviewFlow = reviewFlowRepository.findOne(application.getJobPost().getReviewFlow().getId());
        for (ReviewRun reviewRun: reviewFlow.getRuns()) {
            for (User reviewer: reviewRun.getReviewers()) {
                ReviewResponse response = new ReviewResponse();
                response.setJobApplication(application);
                response.setReviewRun(reviewRun);
                response.setReviewer(reviewer);
                response = reviewResponseRepository.save(response);
                application.addResponse(response);

                /* Review notification for reviewers */
                Notification notification = new Notification();
                notification.setUser(reviewer);
                notification.setContent("You have a new review task: " + reviewRun.getTask());
                notification.setType(Notification.NotificationType.REVIEW_START);
                notification.setItemId(application.getId());
                notificationRepository.save(notification);
            }
        }
        return jobApplicationRepository.save(application);
    }
}
