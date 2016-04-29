package com.worksap.stm2016.controller.roster;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.roster.Schedule;
import com.worksap.stm2016.domain.roster.TimeSlot;
import com.worksap.stm2016.service.ScheduleService;
import com.worksap.stm2016.service.TimeSlotService;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;


@Controller
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TimeSlotService timeSlotService;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/schedule", method = RequestMethod.GET)
    public String schedule(@PathVariable Long id, Locale locale, Model model) {
        model.addAttribute("viewer", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
        model.addAttribute("user", userService.getUserById(id).get());
        return "schedule";
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}/schedule/save", method = RequestMethod.POST)
    public void saveSchedule(@PathVariable Long id, Locale locale,
                             @RequestParam(name = "slotId", required = false) Long slotId,
                             @RequestParam(name = "date", required = false) Integer date,
                             @RequestParam(name = "startTime", required = false) String startTime,
                             @RequestParam(name = "endTime", required = false) String endTime,
                             @RequestParam(name = "scheduleId", required = false) Long scheduleId,
                             User user,
                             @RequestParam(name = "timeZone", required = false) String timeZone,
                             @RequestParam(name = "startDate", required = false) Integer startDate) throws ParseException {

        Schedule schedule = new Schedule();
        schedule.setId(scheduleId);
        schedule.setUser(user);
        schedule.setStartDate(startDate);
        schedule.setTimeZone(timeZone);
        Schedule newSchedule = scheduleService.create(schedule);

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(slotId);
        timeSlot.setDate(date);
        timeSlot.setEndTime(endTime);
        timeSlot.setStartTime(startTime);
        timeSlot.setSchedule(schedule);
        timeSlot.setUser(user);
        timeSlotService.createOrUpdate(timeSlot);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}/schedule/search", method = RequestMethod.POST)
    public JSONObject Schedule(@PathVariable Long id, Locale locale,
                               User user,
                               @RequestParam(name = "startDate") Integer startDate) throws ParseException {
        JSONObject result = new JSONObject();
        Optional<Schedule> schedule = scheduleService.getScheduleByUserAndStartDate(user, startDate);
        if (schedule.isPresent()) {
            Collection<TimeSlot> timeSlots = timeSlotService.getAllTimeSlotBySchedule(schedule.get());
            result.put("scheduleId", schedule.get().getId());
            result.put("timeSlots", timeSlots);
            return result;
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}/schedule/count", method = RequestMethod.POST)
    public Integer Schedule(@PathVariable Long id, Locale locale,
                            User user) throws ParseException {
        Collection schedules = scheduleService.getAllSchedulesByUser(user);
        return schedules.size();
    }

}