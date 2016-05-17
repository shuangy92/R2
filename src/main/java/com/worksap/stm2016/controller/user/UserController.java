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
    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUserResigned(principal, #id)")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserProfilePage(@PathVariable Long id) {
        return "user/user_profile_form";
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String getUserProfilePage() {
        return "user/user_profile_form";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUserResigned(principal, #id)")
    @RequestMapping(value = "/career/user/{id}", method = RequestMethod.GET)
    public String getApplicantProfilePage(@PathVariable Long id) {
        return "career/applicant_profile";
    }

    @RequestMapping(value = "/career/user/profile", method = RequestMethod.GET)
    public String getApplicantProfilePage() {
        return "career/applicant_profile";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user/user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user/user_create";
        }
        // ok, redirect
        return "redirect:/user/user_list";
    }

    @RequestMapping(value = "/account_setting", method = RequestMethod.GET)
    public String getAccountSettingPage() {
        return "account_setting";
    }
}
