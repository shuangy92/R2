package com.worksap.stm2016.controller.recruitment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobPostController {

    private static final Logger logger = LoggerFactory.getLogger(JobPostController.class);

    @RequestMapping(value = "/job_post/create", method = RequestMethod.GET)
    public String getJobPostFormPage() {
        return "recruitment/job_post_form";
    }

    @RequestMapping(value = "/job_post/{id}", method = RequestMethod.GET)
    public String getJobPostFormPage(@PathVariable Long id) {
        return "recruitment/job_post_form";
    }

    @RequestMapping(value = "/job_post", method = RequestMethod.GET)
    public String getJobPostListPage() {
        return "recruitment/job_post_list";
    }

    @RequestMapping(value = "/job_application", method = RequestMethod.GET)
    public String getJobApplicationListPage() {
        return "recruitment/job_application_list";
    }
}