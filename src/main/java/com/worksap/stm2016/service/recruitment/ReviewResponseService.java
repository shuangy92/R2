package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.review.ReviewFlow;
import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.domain.user.User;
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
    NotificationService notificationService;
    @Autowired
    JobApplicationRepository jobApplicationRepository;

    public ReviewResponse get(Long id){
        return reviewResponseRepository.findOne(id);
    }

    public ReviewResponse update(ReviewResponse reviewResponse){
        if (reviewResponse.getStatus() != ReviewResponse.ReviewStatus.REVIEWING) {
            JobApplication jobApplication = jobApplicationRepository.findOne(reviewResponse.getJobApplication().getId());
            jobApplication.increaseNewfinished();
            jobApplicationRepository.save(jobApplication);
        }
        return reviewResponseRepository.save(reviewResponse);
    }


    public List<ReviewResponse.ReviewStatus> getStatusList(List<Integer> responses){
        List<ReviewResponse.ReviewStatus> results = new ArrayList<>();
        for (Integer id: responses) {
            results.add(reviewResponseRepository.findOne(Long.valueOf(id)).getStatus());
        }
        return results;
    }
    /*public boolean isFinishProfileReview(List<Long> responses){
        List<ReviewResponse.ReviewStatus> results = new ArrayList<>();
        for (Long id: responses) {
            if (reviewResponseRepository.findOne(id).getStatus() == ReviewResponse.ReviewStatus.REVIEWING) {
                return false;
            }
        }
        return true;
    }*/
   /* public ReviewResponse updateResponseOfJobApplication(Long jobApplicationId, ReviewResponse reviewResponse){
        boolean finishRun = true;
        JobApplication jobApplication = jobApplicationRepository.findOne(jobApplicationId);
        reviewResponse.setJobApplication(jobApplication);
        reviewResponse = reviewResponseRepository.save(reviewResponse);

        for (ReviewResponse response: jobApplication.getResponses()) {
            if (response.getReviewRun().getId() == reviewResponse.getReviewRun().getId()) {
                if (response.getStatus() == ReviewStatus.REVIEWING) {
                    finishRun = false;
                }
            }
        }
        // Review notification for HR
        if (finishRun) {
            notificationService.createReviewNotification(jobApplication, reviewResponse, Notification.NotificationType.REVIEW_UPDATE_HR);

            // Review notification for reviewers
            for (ReviewResponse response: jobApplication.getResponses()) {
                if (response.getReviewRun().getRunNumber() == reviewResponse.getReviewRun().getRunNumber() + 1) {
                    notificationService.createReviewNotification(jobApplication, response, Notification.NotificationType.REVIEW_UPDATE);
                }
            }
        }
        reviewResponse.setJobApplication(jobApplication);
        return reviewResponse;
    }*/

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
}
