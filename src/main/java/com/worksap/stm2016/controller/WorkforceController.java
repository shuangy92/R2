package com.worksap.stm2016.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WorkforceController {

    private static final Logger logger = LoggerFactory.getLogger(WorkforceController.class);

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @RequestMapping(value = "/workforce", method = RequestMethod.GET)
    public String getWorkforcePage() {
        return "workforce/workforce";
    }
}