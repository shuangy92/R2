package com.worksap.stm2016.api.recruitment;

import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.Contract;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.UserProfile;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.service.job.ContractService;
import com.worksap.stm2016.service.recruitment.JobApplicationService;
import com.worksap.stm2016.service.recruitment.JobPostService;
import com.worksap.stm2016.service.user.UserProfileService;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/job_application")
public class JobApplicationApi {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationApi.class);
    @Autowired
    JobApplicationService jobApplicationService;
    @Autowired
    JobPostService jobPostService;
    @Autowired
    UserService userService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    ContractService contractService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public JobApplication get(@PathVariable("id") Long id){
        return jobApplicationService.get(id);
    }

    @RequestMapping(value="/check/{jobPostId}", method = RequestMethod.GET)
    public JobApplication getByJobPostAndApplicant(Authentication authentication, @PathVariable("jobPostId") Long jobPostId) {
        User applicant = ((CurrentUser) authentication.getPrincipal()).getUser();
        JobApplication JobApplication = jobApplicationService.getByJobPostAndApplicant(jobPostService.get(jobPostId), applicant);
        return JobApplication;
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter
    ) throws org.json.simple.parser.ParseException {

       return jobApplicationService.getList( sort, order, limit, offset, filter);
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public JobApplication saveOrUpdate(@RequestBody JobApplication jobApplication){
        JobApplication myJobApplication= jobApplicationService.getByJobPostAndApplicant(jobApplication.getJobPost(), jobApplication.getApplicant());
        if (myJobApplication != null) {
            jobApplication.setId(myJobApplication.getId());
        }
        return jobApplicationService.save(jobApplication);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public JobApplication save(@RequestBody JobApplication jobApplication){
        return jobApplicationService.save(jobApplication);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobApplication update(@RequestBody JobApplication jobApplication){
        if (jobApplication.getStatus() == JobApplication.JobApplicationStatus.PASSED) {
            JobPost jobPost = jobApplication.getJobPost();
            jobPost.setVacancies(jobPost.getVacancies()-1);
            jobPostService.update(jobPost);
            Contract contract = new Contract();
            contract.setJob(jobPost.getJob());
            contract.setStartDate(jobPost.getStartDate());
            contract.setEndDate(jobPost.getEndDate());
            contract.setPayRate(jobPost.getPayRate());
            contract.setSalary(jobPost.getSalary());
            contract = contractService.save(contract);

            User applicant = jobApplication.getApplicant();
            applicant.setRole(Role.EMPLOYEE);
            applicant.setDepartment(jobApplication.getJobPost().getDepartment());
            userService.update(applicant);

            UserProfile userProfile = userProfileService.get(applicant.getId());
            if (userProfile == null) {
                userProfile = new UserProfile();
            }
            userProfile.setContract(contract);
            userProfileService.saveOrUpdate(userProfile);
        }
        return jobApplicationService.update(jobApplication);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestBody JobApplication jobApplication){
        jobApplicationService.delete(jobApplication);
    }

    /*@RequestMapping(method = RequestMethod.DELETE)
    public void deleteList(@RequestBody ArrayList<Long> ids){
        jobApplicationService.deleteList(ids);
    }*/
}
