package com.worksap.stm2016.controller.recruitment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationController.class);

    @RequestMapping(value = "/job_application", method = RequestMethod.GET)
    public String getJobApplicationListPage() {
        return "recruitment/job_application_list";
    }

    @RequestMapping(value = "/job_application/{id}", method = RequestMethod.GET)
    public String getReviewDetailPage(@PathVariable Long id) {
        return "recruitment/job_application";
    }
}