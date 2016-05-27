package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.recruitment.JobApplicationRepository;
import com.worksap.stm2016.repository.recruitment.JobPostRepository;
import com.worksap.stm2016.service.job.ContractService;
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
    JobPostRepository jobPostRepository;
    @Autowired
    ContractService contractService;
    @Autowired
    UserService userService;
    @Autowired
    ReviewResponseService reviewResponseService;

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
                String search = "";
                List<JobApplication.JobApplicationStatus> statusList = new ArrayList<>();
                if (key.equals("statusList")) {
                    for (String status : (List<String>) filterObj.get(key)) {
                        statusList.add(JobApplication.JobApplicationStatus.valueOf(status));
                    }
                } else {
                    search = (String) filterObj.get(key);
                }
                Specification spec;
                if (key.equals("department")) {
                    spec = isValue("jobPost", "department", "name", search);
                } else if (key.equals("location")) {
                    spec = isValue("jobPost", "department", "location", search);
                } else if (key.equals("title")) {
                    spec = hasValue("jobPost", "title", search);
                } else if (key.equals("jobPostId")) {
                    spec = isValue("jobPost", "id", Long.parseLong(search));
                } else if (key.equals("status")) {
                    spec = isValue(key, JobApplication.JobApplicationStatus.valueOf(search));
                } else if (key.equals("statusList")) {
                    spec = inValue("status", statusList);
                } else { // key = uid
                    spec = isValue("applicant", "id", Long.parseLong(search));
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
            case PASSED:
                jobPost.setVacancies(jobPost.getVacancies() - 1);
                jobPostRepository.save(jobPost);

                Contract contract = contractService.create(jobApplication);

                User user = jobApplication.getApplicant();
                user.setRole(Role.EMPLOYEE);
                user.setDepartment(contract.getJob().getDepartment());
                user.setContract(contract);
                userService.update(user);
                break;
            case SUBMITTED:
                if (jobPost.getReviewFlow() != null) {
                    reviewResponseService.createResponseList(jobApplication);
                }
                break;
            case OFFER_ACCEPTED:
                Collection<JobApplication> jobApplications = jobApplicationRepository.findByApplicant(jobApplication.getApplicant());
                for (JobApplication otherApplications : jobApplications) {
                    if (otherApplications != jobApplication) {
                        otherApplications.setStatus(JobApplication.JobApplicationStatus.CLOSED);
                        jobApplicationRepository.save(otherApplications);
                    }
                }
                break;
            case OFFER_DECLINED:
                jobPost.setVacancies(jobPost.getVacancies() + 1);
                jobPostRepository.save(jobPost);
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
}
