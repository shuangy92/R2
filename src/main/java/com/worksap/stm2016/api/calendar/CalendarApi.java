package com.worksap.stm2016.api.calendar;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.calendar.CalendarEvent;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.service.calendar.CalendarService;
import com.worksap.stm2016.service.user.UserService;
import com.worksap.stm2016.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/calendar")
public class CalendarApi {

    private static final Logger logger = LoggerFactory.getLogger(CalendarApi.class);

    @Autowired
    CalendarService calendarService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<CalendarEvent> getEvents(@RequestParam(name = "id") Long id,
                                         @RequestParam(name = "start") String start,
                                         @RequestParam(name = "end") String end) {
        return calendarService.getEventsBetween(id, DateUtil.parseDate(start, "yyyy-MM-dd"), DateUtil.parseDate(end, "yyyy-MM-dd"));
    }

    @RequestMapping(method = RequestMethod.POST)
    public CalendarEvent save(@RequestBody CalendarEvent calendarEvent) {
        logger.debug("calendar event saved: {}" + calendarEvent);
        return calendarService.save(calendarEvent);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestBody CalendarEvent calendarEvent) {
        logger.debug("calendar event deleted: {}" + calendarEvent);
        calendarService.delete(calendarEvent);
    }

    @RequestMapping(value = "filter", method = RequestMethod.POST)
    public JsonArrayResponse filter(@RequestBody Query query) throws ParseException {
        List<User> freeUsers = new ArrayList<>(userService.getList("name", "asc", 1000, 0, query.filter).getRows());
        long total = freeUsers.size();
        if (total == 0 || query.start == null || query.end == null) {
            return new JsonArrayResponse(new ArrayList<>(), 0);
        } else {
            Map<User, Float> userFreeTime = calendarService.getUserFreeTimeBetween(query.start, query.end, freeUsers);

            List<User> busyUsers = new ArrayList<>(userFreeTime.keySet());
            List<Float> availabilities = new ArrayList<>(userFreeTime.values());
            freeUsers.removeAll(busyUsers);

            List<UserAvailability> rows = new ArrayList<>();

            for (int i = query.offset; i < query.offset + query.limit; i++) {
                int j = i - freeUsers.size();
                if (i < freeUsers.size()) {
                    rows.add(new UserAvailability(freeUsers.get(i)));
                } else if (j < busyUsers.size()) {
                    rows.add(new UserAvailability(busyUsers.get(j), availabilities.get(j)));
                }
            }
            return new JsonArrayResponse(rows, total);
        }
    }

    @Getter
    @Setter
    private static class Query {
        Date start;
        Date end;
        int limit;
        int offset;
        String filter;
    }

    @Getter
    @Setter
    private static class UserAvailability {
        User user;
        float availability;
        UserAvailability(User user, float availability) {
            this.user = user;
            this.availability = availability;
        }
        UserAvailability(User user) {
            this.user = user;
            availability = -1;
        }
    }
}
