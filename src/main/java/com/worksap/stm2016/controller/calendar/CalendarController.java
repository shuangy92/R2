package com.worksap.stm2016.controller.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CalendarController {

    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/user/calendar", method = RequestMethod.GET)
    public String getCalendarPage() {
        return "calendar/calendar";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/calendar", method = RequestMethod.GET)
    public String getUserCalendarPage(@PathVariable Long id) {
        return "calendar/calendar";
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/schedule_filter", method = RequestMethod.GET)
    public String getScheduleFilterPage() {
        return "calendar/schedule_filter";
    }

}