package com.worksap.stm2016.controller.recruitment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationController.class);

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/job_application", method = RequestMethod.GET)
    public String getJobApplicationListPage() {
        return "recruitment/job_application_list";
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/job_application/{id}", method = RequestMethod.GET)
    public String getJobApplicationPage(@PathVariable Long id) {
        return "recruitment/job_application";
    }

    /*@PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/job_application/profile_review", method = RequestMethod.GET)
    public String getProfileReviewListPage() {
        return "recruitment/job_application_review_list";
    }*/

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/job_application/profile_review", method = RequestMethod.GET)
    public String getProfileReviewPage(@RequestParam(name="mode", required = false) String mode) {
        if (mode == null) {
            return "recruitment/job_application_review_list";
        } else { // if (mode.equals("slideshow"))
            return "recruitment/job_application_review_slideshow";
        }
    }
}