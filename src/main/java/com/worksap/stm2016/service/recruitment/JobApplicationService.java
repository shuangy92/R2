package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.calendar.CalendarEvent;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.calendar.CalendarEventRepository;
import com.worksap.stm2016.repository.recruitment.JobApplicationRepository;
import com.worksap.stm2016.repository.recruitment.JobPostRepository;
import com.worksap.stm2016.repository.recruitment.ReviewResponseRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import com.worksap.stm2016.service.message.NotificationService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class JobApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationService.class);
    @Autowired
    JobApplicationRepository jobApplicationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewResponseRepository reviewResponseRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    JobPostRepository jobPostRepository;
    @Autowired
    CalendarEventRepository calendarEventRepository;


    public JobApplication get(Long id){
        return jobApplicationRepository.findOne(id);
    }

    public JsonArrayResponse getList(String sort, String order, Integer limit, Integer offset, String filter
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
                        if (statusList.size() == 0) {
                            return new JsonArrayResponse(new ArrayList<>(), 0);
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

        return andFilter( sort,  order,  limit,  offset,  filter,  specs, jobApplicationRepository);
    }

    public List<JobApplication> getReviewList(List<Integer> ids) throws org.json.simple.parser.ParseException {
        List<JobApplication> rows = new ArrayList<>();
        for (Integer id : ids) {
            rows.add(jobApplicationRepository.findOne(Long.valueOf(id)));
        }
        return rows;
    }

    public JobApplication getByJobPostAndApplicant(JobPost jobPost, User applicant) {
        return jobApplicationRepository.findOneByJobPostAndApplicant(jobPost, applicant);
    }
    public JobApplication save(JobApplication jobApplication){
        try{
            return jobApplicationRepository.save(jobApplication);
        } catch(Exception e){
            return null;
        }
    }

    public JobApplication update(JobApplication jobApplication){
        JobPost jobPost = jobApplication.getJobPost();
        switch (jobApplication.getStatus()) {
            case CONTRACTED:
                // close other applications of the applicant
                Collection<JobApplication> jobApplications = jobApplicationRepository.findByApplicant(jobApplication.getApplicant());
                for (JobApplication otherApplications : jobApplications) {
                    if (otherApplications != jobApplication) {
                        otherApplications.setStatus(JobApplication.JobApplicationStatus.CLOSED);
                        jobApplicationRepository.save(otherApplications);
                    }
                }

                jobPost.decreaseVacancies();

                // close other applications of the job post
                if (!jobPost.getOpen()) {
                    jobApplications = jobApplicationRepository.findByJobPost(jobPost);
                    for (JobApplication otherApplications : jobApplications) {
                        if (otherApplications != jobApplication) {
                            if (otherApplications.getStatus() == JobApplication.JobApplicationStatus.SUBMITTED ||
                                    otherApplications.getStatus() == JobApplication.JobApplicationStatus.REVIEWING ||
                                    otherApplications.getStatus() == JobApplication.JobApplicationStatus.OFFER_SENT ||
                                    otherApplications.getStatus() == JobApplication.JobApplicationStatus.OFFER_ACCEPTED) {
                                otherApplications.setStatus(JobApplication.JobApplicationStatus.CLOSED);
                                jobApplicationRepository.save(otherApplications);
                            }
                        }
                    }
                }

                jobPostRepository.save(jobPost);

                User user = jobApplication.getApplicant();
                user.setRole(Role.EMPLOYEE);
                userRepository.save(user);
                break;
        }
        try{
            return jobApplicationRepository.save(jobApplication);
        } catch(Exception e){
            return null;
        }
    }

    public void delete(Long id){
        jobApplicationRepository.delete(id);
    }

    public void delete(JobApplication jobApplication){
        jobApplicationRepository.delete(jobApplication);
    }

    public Long deleteList(ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                jobApplicationRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }
    public void handleProfileReview(List<Long> ids, List<Long> reviewers, String notes, User sender){
        List <JobApplication> applications = new ArrayList<>();
        for (long id : ids) {
            // update status
            JobApplication jobApplication = jobApplicationRepository.findOne(id);
            jobApplication.setStatus(JobApplication.JobApplicationStatus.REVIEWING);
            applications.add(jobApplication);
        }

        // add notification
        for (Long reviewerId : reviewers) {
            List <Long> responseIds = new ArrayList<>();

            for (JobApplication jobApplication : applications) {
                // update status
                User reviewer = new User();
                reviewer.setId(reviewerId);

                ReviewResponse reviewResponse = new ReviewResponse();
                reviewResponse.setReviewer(reviewer);
                reviewResponse.setType(ReviewResponse.ReviewType.PROFILE_REVIEW);
                reviewResponse.setRunNumber((short) (jobApplication.getResponses().size() + 1));
                responseIds.add(reviewResponseRepository.save(reviewResponse).getId());
                jobApplication.addResponse(reviewResponse);
            }

            User reviewer = new User();
            reviewer.setId(reviewerId);
            notificationService.createProfileReviewNotification(reviewer, sender, ids, responseIds, notes);
        }

        for (JobApplication jobApplication : applications) {
            jobApplicationRepository.save(jobApplication);
        }
    }

    public void handleInterview(JobApplication jobApplication, User interviewer, String notes, Date start, Date end, User sender) {
        // update status
        jobApplication = jobApplicationRepository.findOne(jobApplication.getId());
        jobApplication.setStatus(JobApplication.JobApplicationStatus.REVIEWING);
        // add response
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setReviewer(interviewer);
        reviewResponse.setType(ReviewResponse.ReviewType.INTERVIEW);
        reviewResponse.setRunNumber((short) (jobApplication.getResponses().size() + 1));
        reviewResponse.setStart(start);
        reviewResponse.setEnd(end);
        Long responseId = reviewResponseRepository.save(reviewResponse).getId();
        jobApplication.addResponse(reviewResponse);

        jobApplicationRepository.save(jobApplication);

        // add calendar event
        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setAuthor(sender);
        calendarEvent.setDescription(notes);
        calendarEvent.setStart(start);
        calendarEvent.setEnd(end);
        calendarEvent.setTitle("Interview");
        calendarEvent.setUser(interviewer);
        calendarEventRepository.save(calendarEvent);

        // add notification
        notificationService.createInterviewNotification(jobApplication.getId(), responseId, interviewer, sender, notes, start, end);
    }

}
