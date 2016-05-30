package com.worksap.stm2016.controller;

import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER') or hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "notification_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String getSettingPage() {
        return "setting";
    }
}