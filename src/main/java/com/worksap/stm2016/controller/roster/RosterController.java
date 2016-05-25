package com.worksap.stm2016.controller.roster;

import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.domain.roster.Roster;
import com.worksap.stm2016.domain.roster.Shift;
import com.worksap.stm2016.domain.roster.TimeSlot;
import com.worksap.stm2016.service.roster.RosterService;
import com.worksap.stm2016.service.roster.ScheduleService;
import com.worksap.stm2016.service.roster.ShiftService;
import com.worksap.stm2016.service.roster.TimeSlotService;
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
import java.util.Optional;

@Controller
public class RosterController {

    private static final Logger logger = LoggerFactory.getLogger(RosterController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private RosterService rosterService;
    @Autowired
    private ShiftService shiftService;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/roster", method = RequestMethod.GET)
    public String schedule(@PathVariable Long id, Locale locale, Model model) {
        model.addAttribute("viewer", userService.get(id));
        return "roster";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @ResponseBody
    @RequestMapping(value = "/user/{id}/roster/save", method = RequestMethod.POST)
    public void saveSchedule(@PathVariable Long id, Locale locale,
                             @RequestParam(name = "shiftId") Long shiftId,
                             @RequestParam(name = "date", required = false) Integer date,
                             @RequestParam(name = "startTime", required = false) String startTime,
                             @RequestParam(name = "endTime", required = false) String endTime,
                             @RequestParam(name = "rosterId", required = false) Long scheduleId,
                             @RequestParam(name = "slotId", required = false) Long slotId,
                             User author,
                             @RequestParam(name = "timeZone", required = false) String timeZone,
                             @RequestParam(name = "startDate", required = false) Integer startDate) throws ParseException {
        Roster roster = new Roster();
        roster.setId(scheduleId);
        roster.setAuthor(author);
        roster.setStartDate(startDate);
        roster.setTimeZone(timeZone);

        Roster newRoster = rosterService.create(roster);
        logger.debug("{}", newRoster);

        Shift shift = new Shift();
        shift.setId(shiftId);
        shift.setDate(date);
        shift.setEndTime(endTime);
        shift.setStartTime(startTime);
        shift.setRoster(roster);
        if (slotId != null && slotId != 0) {
            Optional<TimeSlot> timeSlot = timeSlotService.getTimeSlotById(slotId);
            if (timeSlot.isPresent()) {
                shift.setTimeSlot(timeSlot.get());
            }
            shift.setUser(timeSlot.get().getUser());

        }
        shiftService.createOrUpdate(shift);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @ResponseBody
    @RequestMapping(value = "/user/{id}/roster/search", method = RequestMethod.POST)
    public JSONObject findRoster(@PathVariable Long id, Locale locale,
                                 User author,
                                 @RequestParam(name = "startDate") Integer startDate) throws ParseException {
        JSONObject result = new JSONObject();
        Optional<Roster> roster = rosterService.getRosterByAuthorAndStartDate(author, startDate);
        if (roster.isPresent()) {
            Collection<Shift> shifts = shiftService.getAllShiftByRoster(roster.get());
            result.put("rosterId", roster.get().getId().toString());
            result.put("shifts", shifts);
            return result;
        } else {
            return null;
        }
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @ResponseBody
    @RequestMapping(value = "/user/{id}/roster/search_candidate", method = RequestMethod.POST)
    public Collection findCandidates(@PathVariable Long id,
                                     @RequestParam(name = "date") Integer date,
                                     @RequestParam(name = "startTime", required = false) String startTime,
                                     @RequestParam(name = "endTime", required = false) String endTime) throws ParseException {
        Collection<TimeSlot> timeSlots = timeSlotService.getAllSchedulesByDateAndAvailableTime(date, startTime, endTime);
        /*List result = new ArrayList();

        for (TimeSlot ts : timeSlots) {
            Schedule schedule = ts.getSchedule();
            JSONObject obj = new JSONObject();
            obj.put("user", schedule.getUser());
            obj.put("timeSlot", ts);
        }
        logger.debug("result: {}", result);*/
        return timeSlots;
    }
}