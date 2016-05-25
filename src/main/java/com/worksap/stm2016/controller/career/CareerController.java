package com.worksap.stm2016.controller.career;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CareerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CareerController.class);

    @RequestMapping(value = "/career", method = RequestMethod.GET)
    public String getCareerPage() {
        return "career/career";
    }

    @RequestMapping(value = "/career/register", method = RequestMethod.GET)
    public String getUserRegisterPage() {
        return "user/user_register_form";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/career/job_application", method = RequestMethod.GET)
    public String getApplicantDashboardPage() {
        return "career/applicant_application_list";
    }

    @PreAuthorize("hasAuthority('APPLICANT')")
    @RequestMapping(value = "/career/applicant_profile_submit", method = RequestMethod.GET)
    public String getApplicationSubmitPage() {
        return "career/applicant_profile_form";
    }

    @RequestMapping(value = "/career/job_post/{id}", method = RequestMethod.GET)
    public String getJobPostPage(@PathVariable Long id) {
        return "career/career_job_post";
    }
}
