package com.worksap.stm2016.controller.user;

import com.worksap.stm2016.audit.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

    public CurrentUser getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }


}
