package com.worksap.stm2016.controller.recruitment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewFlowController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewFlowController.class);

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/review_flow", method = RequestMethod.GET)
    public String getReviewFlowListPage() {
        return "recruitment/review_flow_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/review_flow/{id}", method = RequestMethod.GET)
    public String getReviewFlowCreatePage(@PathVariable Long id) {
        return "recruitment/review_flow_form";
    }
}