package com.worksap.stm2016.controller;

import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/logged_in", method = RequestMethod.GET)
    public String loggedIn(Authentication authentication) {
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        if (user.getRole() == Role.APPLICANT) {
            return "redirect:/career";
        } else {
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard";
    }
}