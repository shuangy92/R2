package com.worksap.stm2016.controller.user;

import com.worksap.stm2016.repository.UserRepository;
import com.worksap.stm2016.service.ScheduleService;
import com.worksap.stm2016.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user")
    public String getUsersPage() {
        return "user/user_list";
    }

}