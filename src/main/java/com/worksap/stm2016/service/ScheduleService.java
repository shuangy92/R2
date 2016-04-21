package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.Schedule;
import com.worksap.stm2016.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface ScheduleService {

    Optional<Schedule> getScheduleById(long id);

    Optional<Schedule> getScheduleByUserAndStartDate(User user, Integer startDate);

    Collection<Schedule> getAllSchedulesByUser(User user);

    Collection<Schedule> getAllSchedulesByStartDate(Integer startDate);

    Collection<Schedule> getAllSchedules();

    Long getScheduleCountByUser(User user);

    Schedule create(Schedule schedule);
}