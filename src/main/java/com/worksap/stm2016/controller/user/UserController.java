package com.worksap.stm2016.controller.user;

import com.worksap.stm2016.form.UserCreateForm;
import com.worksap.stm2016.form.validator.UserCreateFormValidator;
import com.worksap.stm2016.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserProfilePage(@PathVariable Long id) {
        return "user/user_profile_form";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String getUserProfilePage() {
        return "user/user_profile_form";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/career/user/profile", method = RequestMethod.GET)
    public String getApplicantProfilePage() {
        return "career/applicant_profile";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user/account_setting", method = RequestMethod.GET)
    public String getAccountSettingPage() {
        return "account_setting";
    }
}
