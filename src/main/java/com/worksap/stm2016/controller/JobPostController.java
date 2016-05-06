package com.worksap.stm2016.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobPostController {

    private static final Logger logger = LoggerFactory.getLogger(JobPostController.class);

    @RequestMapping(value = "/post_a_job", method = RequestMethod.GET)
    public String getJobPostFormPage() {
        return "job/job_post_form";
    }

    @RequestMapping(value = "/job_posts", method = RequestMethod.GET)
    public String getJobPostListPage() {
        return "job/job_posts";
    }
}