package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.Schedule;
import com.worksap.stm2016.domain.TimeSlot;
import com.worksap.stm2016.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface TimeSlotService {

    Optional<TimeSlot> getTimeSlotById(long id);

    Collection<TimeSlot> getAllTimeSlotBySchedule(Schedule schedule);

    Collection<TimeSlot> getAllTimeSlotByDate(User user);

    Optional<TimeSlot> getTimeSlotByScheduleAndDate(Schedule schedule, Integer date);

    Collection<TimeSlot> getAllSchedulesByDateAndAvailableTime(Integer date, String startTime, String endTime);

    TimeSlot createOrUpdate(TimeSlot timeSlot);
}