package com.worksap.stm2016.controller.user;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.UserProfile;
import com.worksap.stm2016.form.UserCreateForm;
import com.worksap.stm2016.form.validator.UserCreateFormValidator;
import com.worksap.stm2016.repository.UserProfileRepository;
import com.worksap.stm2016.service.ScheduleService;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
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
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUserResigned(principal, #id)")
    @RequestMapping(value = "/user/{id}/profile", method = RequestMethod.GET, produces = "text/html")
    public String getUserPage(@PathVariable Long id) {
        return "user";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUserResigned(principal, #id)")
    @ResponseBody
    @RequestMapping(value = "/user/{id}/profile", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getUserProfile(@PathVariable Long id) {
        JSONObject result = new JSONObject();
        User user = userService.getUserById(id).get();
        result.put("user", user);
        UserProfile userProfile = userProfileRepository.findOneByUser(user);
        if (userProfile != null) {
            result.put("userProfile", userProfile);
        }
        return result;
    }
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @ResponseBody
    @RequestMapping(value = "/user/{id}/profile", method = RequestMethod.POST)
    public String updateUserProfile(@PathVariable Long id,
                                    @RequestParam(name="phone", required = false) String phone,
                                    @RequestParam(name="address", required = false) String address) {
        User user = userService.getUserById(id).get();
        UserProfile userProfile = userProfileRepository.findOneByUser(user);
        if (userProfile == null) {
            userProfile = new UserProfile();
            userProfile.setUser(user);
        }
        userProfile.setPhone(phone);
        userProfile.setAddress(address);
        userProfileRepository.save(userProfile);
        return "Profile has been saved";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id).get();
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id) {
        User user = userService.getUserById(id).get();
        user.setActive(false);
        userService.update(user);
        return "Employee " + user.getName() + " has resigned";
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
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        // ok, redirect
        return "redirect:/users";
    }
}
