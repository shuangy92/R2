package com.worksap.stm2016.controller.career;

import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.form.validator.UserCreateFormValidator;
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
public class CareerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CareerController.class);

    @RequestMapping(value = "/career", method = RequestMethod.GET)
    public String getCareerPage() {
        return "career/career";
    }

    @RequestMapping(value = "/career/register", method = RequestMethod.GET)
    public String getUserRegisterPage() {
        LOGGER.debug("Getting user create form");
        return "user/user_register_form";
    }

    @PreAuthorize("hasAuthority('APPLICANT')")
    @RequestMapping(value = "/career/application_submit", method = RequestMethod.GET)
    public String getApplicationSubmitPage() {
        LOGGER.debug("Getting user create form");
        return "career/application_submit";
    }
}
