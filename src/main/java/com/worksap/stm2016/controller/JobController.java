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

    @RequestMapping(value = "/job_profile", method = RequestMethod.GET)
    public String getJobListPage() {
        return "job/job_list";
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/job_profile/create", method = RequestMethod.GET)
    public String getJobProfilePage() {
        return "job/job_profile_form";
    }

    @RequestMapping(value = "/job_profile/{id}", method = RequestMethod.GET)
    public String getJobProfilePage(@PathVariable Long id) {
        return "job/job_profile_form";
    }

    @RequestMapping(value = "/job_category", method = RequestMethod.GET)
    public String getJobCategoryListPage() {
        return "job/job_category_list";
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public String getDepartmentListPage() {
        return "job/department_list";
    }

    @RequestMapping(value = "/department/create", method = RequestMethod.GET)
    public String getDepartmentPage() {
        return "job/department_form";
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public String getDepartmentPage(@PathVariable Long id) {
        return "job/department_form";
    }
}