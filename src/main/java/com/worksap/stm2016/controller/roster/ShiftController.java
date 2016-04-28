package com.worksap.stm2016.controller.roster;

import com.worksap.stm2016.domain.roster.Shift;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.service.ScheduleService;
import com.worksap.stm2016.service.ShiftService;
import com.worksap.stm2016.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;


@Controller
public class


ShiftController {

    private static final Logger logger = LoggerFactory.getLogger(ShiftController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ShiftService shiftService;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/shifts", method = RequestMethod.GET)
    public String schedule(@PathVariable Long id, Locale locale, Model model) {
        model.addAttribute("user", userService.getUserById(id).get());
        return "shifts";
    }

    @ResponseBody
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/shifts", method = RequestMethod.POST)
    public List<Collection> shifts(@PathVariable Long id, Locale locale,
                                   User user,
                                   @RequestParam(value = "dates[]") Integer[] dates) {
        List<Collection> shiftList = new ArrayList<Collection>();
        for (Integer date : dates) {
            Collection shifts = shiftService.getAllShiftByDateAndUser(date, user);
            shiftList.add(shifts);
        }
        return shiftList;
    }

    @ResponseBody
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/shifts/today", method = RequestMethod.POST)
    public Collection<Shift> shiftToday(@PathVariable Long id, Locale locale,
                                        User user,
                                        @RequestParam(value = "date") Integer date) {
        return shiftService.getAllShiftByDateAndUser(date, user);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}/shift/check_in", method = RequestMethod.POST)
    public void checkIn(@PathVariable Long id, Locale locale,
                        Shift shift) throws ParseException {

    }
}