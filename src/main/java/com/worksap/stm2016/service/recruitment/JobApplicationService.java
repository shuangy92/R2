package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.domain.review.ReviewFlow;
import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.recruitment.JobApplicationRepository;
import com.worksap.stm2016.repository.recruitment.JobPostRepository;
import com.worksap.stm2016.repository.recruitment.ReviewFlowRepository;
import com.worksap.stm2016.repository.recruitment.ReviewResponseRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import com.worksap.stm2016.service.job.ContractService;
import com.worksap.stm2016.service.message.NotificationService;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class JobApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationService.class);
    @Autowired
    JobApplicationRepository jobApplicationRepository;
    @Autowired
    ContractService contractService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewResponseRepository reviewResponseRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    JobPostRepository jobPostRepository;

    public JobApplication get(Long id){
        return jobApplicationRepository.findOne(id);
    }

    public JSONObject getList(String sort, String order, Integer limit, Integer offset, String filter
    ) throws org.json.simple.parser.ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = key.equals("statusList") ? "" : (String) filterObj.get(key);
                Specification spec = null;
                switch (key) {
                    case "statusList":
                        List<JobApplication.JobApplicationStatus> statusList = new ArrayList<>();
                        for (String status : (List<String>) filterObj.get(key)) {
                            statusList.add(JobApplication.JobApplicationStatus.valueOf(status));
                        }
                        spec = inValue("status", statusList);
                        break;
                    case "status":
                        spec = isValue(key, JobApplication.JobApplicationStatus.valueOf(search));
                        break;
                    case "department":
                        spec = isValue("jobPost", "department", "name", search);
                        break;
                    case "location":
                        spec = isValue("jobPost", "department", "location", search);
                        break;
                    case "title":
                        spec = hasValue("jobPost", "title", search);
                        break;
                    case "jobPostId":
                        spec = isValue("jobPost", "id", Long.parseLong(search));
                        break;
                    case "uid":
                        spec = isValue("applicant", "id", Long.parseLong(search));
                        break;
                }
                specs.add(spec);
            }
        }

        JSONObject result = andFilter( sort,  order,  limit,  offset,  filter,  specs, jobApplicationRepository);
        return result;
    }

    public JobApplication getByJobPostAndApplicant(JobPost jobPost, User applicant) {
        return jobApplicationRepository.findOneByJobPostAndApplicant(jobPost, applicant);
    }
    public JobApplication save(JobApplication application){
        return jobApplicationRepository.save(application);
    }

    public JobApplication update(JobApplication jobApplication){
        JobPost jobPost = jobApplication.getJobPost();
        switch (jobApplication.getStatus()) {
            case REVIEWING:
                if (jobPost.getReviewFlow() != null) {
                    this.createResponseList(jobApplication);
                }
                break;
            case CONTRACTED:
                Collection<JobApplication> jobApplications = jobApplicationRepository.findByApplicant(jobApplication.getApplicant());
                for (JobApplication otherApplications : jobApplications) {
                    if (otherApplications != jobApplication) {
                        otherApplications.setStatus(JobApplication.JobApplicationStatus.CLOSED);
                        jobApplicationRepository.save(otherApplications);
                    }
                }

                jobPost.decreaseVacancies();
                jobPostRepository.save(jobPost);

                User user = jobApplication.getApplicant();
                user.setRole(Role.EMPLOYEE);
                userRepository.save(user);
                break;
        }
        return jobApplicationRepository.save(jobApplication);
    }

    public void delete(Long id){
        jobApplicationRepository.delete(id);
    }

    public void delete(JobApplication jobApplication){
        jobApplicationRepository.delete(jobApplication);
    }

    public Long deleteList(@RequestBody ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                jobApplicationRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }

    public JobApplication createResponseList(JobApplication jobApplication){
        ReviewFlow reviewFlow = jobApplication.getJobPost().getReviewFlow();
        for (ReviewRun reviewRun: reviewFlow.getRuns()) {
            for (User reviewer: reviewRun.getReviewers()) {
                ReviewResponse response = new ReviewResponse();
                response.setReviewRun(reviewRun);
                response.setReviewer(reviewer);
                jobApplication.addResponse(response);

                /* Review notification for reviewers */
                notificationService.createReviewNotification(jobApplication, response, Notification.NotificationType.REVIEW_START);
            }
        }
        return jobApplicationRepository.save(jobApplication);
    }
}
