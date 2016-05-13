package com.worksap.stm2016.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public String getJobListPage() {
        return "job/jobs";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/job/create", method = RequestMethod.GET)
    public String getJobProfilePage() {
        return "job/job_profile_form";
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
    public String getJobProfilePage(@PathVariable Long id) {
        return "job/job_profile_form";
    }

    @RequestMapping(value = "/job_categories", method = RequestMethod.GET)
    public String getJobCategoryListPage() {
        return "job/job_categories";
    }
}