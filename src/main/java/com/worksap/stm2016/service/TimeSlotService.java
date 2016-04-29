package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.roster.Schedule;
import com.worksap.stm2016.domain.roster.TimeSlot;
import com.worksap.stm2016.repository.roster.TimeSlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TimeSlotService  {

    private static final Logger logger = LoggerFactory.getLogger(TimeSlotService.class);

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public Optional<TimeSlot> getTimeSlotById(long id) {
        return Optional.ofNullable(timeSlotRepository.findOne(id));
    }

    public Collection<TimeSlot> getAllTimeSlotBySchedule(Schedule schedule) {
        return timeSlotRepository.findByScheduleOrderByDateAscStartTimeAsc(schedule);
    }

    public Collection<TimeSlot> getAllTimeSlotByDate(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    public Optional<TimeSlot> getTimeSlotByScheduleAndDate(Schedule schedule, Integer date) {
        // TODO Auto-generated method stub
        return null;
    }

    public Collection<TimeSlot> getAllSchedulesByDateAndAvailableTime(Integer date, String startTime, String endTime) {
        if (startTime != null && endTime != null) {
            return timeSlotRepository.findByDateAndAvailableTime(date, startTime, endTime);
        } else if (startTime != null) {
            return timeSlotRepository.findByDateAndStartTime(date, startTime);
        } else if (endTime != null) {
            return timeSlotRepository.findByDateAndEndTime(date, endTime);
        } else {
            return timeSlotRepository.findByDate(date);
        }
    }

    public TimeSlot createOrUpdate(TimeSlot timeSlot) {
        Optional<TimeSlot> oldTimeSlot = this.getTimeSlotById(timeSlot.getId());
        if (oldTimeSlot.isPresent()) {
            if (timeSlot.getStartTime() == null) {
                timeSlot.setStartTime(oldTimeSlot.get().getStartTime());
            }
            if (timeSlot.getEndTime() == null) {
                timeSlot.setEndTime(oldTimeSlot.get().getEndTime());
            }
            return timeSlotRepository.save(timeSlot);
        } else {
            return timeSlotRepository.save(timeSlot);
        }
    }
}
