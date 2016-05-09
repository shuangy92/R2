package com.worksap.stm2016.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CareerController {

    private static final Logger logger = LoggerFactory.getLogger(CareerController.class);

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public String getJobListPage() {
        return "job/jobs";
    }
}