package com.worksap.stm2016.controller.recruitment;

import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewFlowController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewFlowController.class);


    @RequestMapping(value = "/review_flow", method = RequestMethod.GET)
    public String getReviewFlowListPage() {
        return "recruitment/review_flow_list";
    }

    @RequestMapping(value = "/review_flow/{id}", method = RequestMethod.GET)
    public String getReviewFlowCreatePage(@PathVariable Long id) {
        return "recruitment/review_flow_form";
    }

}