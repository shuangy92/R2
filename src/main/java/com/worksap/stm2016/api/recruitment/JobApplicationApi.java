package com.worksap.stm2016.api.recruitment;

import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.service.recruitment.JobApplicationService;
import com.worksap.stm2016.service.recruitment.JobPostService;
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


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JobApplication get(@PathVariable("id") Long id) {
        return jobApplicationService.get(id);
    }

    @RequestMapping(value = "/check/{jobPostId}", method = RequestMethod.GET)
    public JobApplication check(Authentication authentication, @PathVariable("jobPostId") Long jobPostId) {
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

        return jobApplicationService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public JobApplication save(@RequestBody JobApplication jobApplication){
        JobApplication myJobApplication= jobApplicationService.getByJobPostAndApplicant(jobApplication.getJobPost(), jobApplication.getApplicant());
        if (myJobApplication != null) {
            jobApplication.setId(myJobApplication.getId());
        }
        return jobApplicationService.save(jobApplication);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobApplication update(@RequestBody JobApplication jobApplication) {
        return jobApplicationService.update(jobApplication);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestBody JobApplication jobApplication) {
        jobApplicationService.delete(jobApplication);
    }

}
