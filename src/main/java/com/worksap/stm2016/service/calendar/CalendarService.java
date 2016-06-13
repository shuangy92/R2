package com.worksap.stm2016.service.calendar;

import com.worksap.stm2016.domain.calendar.CalendarEvent;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.repository.calendar.CalendarEventRepository;
import com.worksap.stm2016.util.CollectionUtil;
import com.worksap.stm2016.util.DateUtil;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.worksap.stm2016.specification.BasicSpecs.*;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Shuang on 4/25/2016.
 */
@Service
public class CalendarService {

    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);
    @Autowired
    CalendarEventRepository calendarEventRepository;

    public CalendarEvent get(Long id){
        return calendarEventRepository.findOne(id);
    }

    public List<CalendarEvent> getEventsBetween(Long id, Date start, Date end){

        Specifications spec = where(isValue("user", "id", id)).and(geDate("start", start)).and(leDate("end", end));
        return calendarEventRepository.findAll(spec);
    }

    public List<CalendarEvent> getEventsBetween(Date start, Date end, List<User> freeUsers) throws ParseException {

        Specifications spec = where(geDate("start", start)).and(ltDate("start", end));
        spec = spec.or(where(gtDate("end", start)).and(leDate("end", end)));
        spec = spec.or(where(geDate("end", end)).and(leDate("start", start)));
        spec = spec.and(inValue("user", freeUsers));
        return calendarEventRepository.findAll(spec, new Sort(Sort.Direction.ASC, "user"));
    }

    public Map<User, Float> getUserFreeTimeBetween(Date start, Date end, List<User> freeUsers) throws ParseException {
        List<CalendarEvent> events = this.getEventsBetween(start, end, freeUsers);
        Map<User, List<TimeSlot>> userTimeSlots = new HashMap<>();

        TimeSlot query = new TimeSlot(start, end);
        for (CalendarEvent event : events) {
            User key = event.getUser();
            if (userTimeSlots.containsKey(key) && userTimeSlots.get(key) == null) {
                continue;
            }

            TimeSlot other = new TimeSlot(event.getStart(), event.getEnd());
            int result = query.merge(other);
            if (result == 0) {
                userTimeSlots.put(key, null);
                continue;
            } else if (result == 3) {
                other.start = query.start;
            } else if (result == 4) {
                other.end = query.end;
            }

            if (!userTimeSlots.containsKey(key)) {
                userTimeSlots.put(key, new ArrayList<>());
                userTimeSlots.get(key).add(other);
            } else {
                List<TimeSlot> timeSlots = userTimeSlots.get(key);
                for (int i = 0; i < timeSlots.size(); ++i) {
                    TimeSlot e = timeSlots.get(i);
                    result = e.merge(other);
                    if (result == 0) {
                        timeSlots.set(i, other);
                        break;
                    } else if (result == 1) {
                        break;
                    }  else if (result == 3) {
                        e.start = other.start;
                        timeSlots.set(i, e);
                        break;
                    } else if (result == 4) {
                        e.end = other.end;
                        timeSlots.set(i, e);
                        break;
                    }
                }
                if (result == 2) {
                    timeSlots.add(other);
                }
            }
        }

        float duration = DateUtil.diffInMinutes(start, end) / 60f;
        Map<User, Float> userFreeTime = new HashMap<>();
        for (Map.Entry<User, List<TimeSlot>> entry : userTimeSlots.entrySet()) {
            List<TimeSlot> timeSlots = entry.getValue();
            if (timeSlots == null) {
                userFreeTime.put(entry.getKey(), (float) 0);
                continue;
            }
            float busyTime = 0f;
            for (TimeSlot timeSlot : timeSlots) {
                busyTime += timeSlot.getDuration();
            }
            userFreeTime.put(entry.getKey(), duration - busyTime);
        }
        return  CollectionUtil.sortMapByValueDesc(userFreeTime);
    }
  

    public CalendarEvent save(CalendarEvent calendarEvent)  {
        try {
            return calendarEventRepository.save(calendarEvent);
        } catch (Exception e) {
            return null;
        }
    }

    public CalendarEvent update(CalendarEvent calendarEvent)  {
        try {
            return calendarEventRepository.save(calendarEvent);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(CalendarEvent calendarEvent)  {
        calendarEventRepository.delete(calendarEvent);
    }

    private class TimeSlot {
        Date start;
        Date end;
        TimeSlot(Date start, Date end) {
            this.start = new Date(start.getTime());
            this.end = new Date(end.getTime());
        }
        float getDuration() {
            return DateUtil.diffInMinutes(this.start, this.end) / 60f;
        }
        int merge(TimeSlot other) {
            if ((other.start.before(this.start) || other.start.equals(this.start)) &&
                    (other.end.after(this.end) || other.end.equals(this.end))) {// contain slot
                return 0;
            } else if ((other.start.after(this.start) || other.start.equals(this.start)) &&
                    (other.end.before(this.end) || other.end.equals(this.end))) {// inside slot
                return 1;
            } else if ((other.end.before(this.start) || other.end.equals(this.start)) ||
                    (other.start.after(this.end) || other.start.equals(this.end))) {// out of slot
                return 2;
            }  else if (other.start.before(this.start) &&  other.end.after(this.start)) {// contain start
                return 3;
            } else if (other.start.before(this.end) &&  other.end.after(this.end)) {// contain end
                return 4;
            }
            return -1;
        }
    }
}
