package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.Schedule;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.repository.ScheduleRepository;
import com.worksap.stm2016.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Optional<Schedule> getScheduleById(long id) {
        logger.debug("Getting schedule by id={}", id);
        return Optional.ofNullable(scheduleRepository.findOne(id));
    }

    @Override
    public Optional<Schedule> getScheduleByUserAndStartDate(User user, Integer startDate) {
        return scheduleRepository.findOneByUserAndStartDate(user, startDate);
    }

    @Override
    public Collection<Schedule> getAllSchedulesByUser(User user) {
        logger.debug("Getting schedules by user={}", user);
        return scheduleRepository.findByUser(user);
    }

    @Override
    public Collection<Schedule> getAllSchedulesByStartDate(Integer startDate) {
        return scheduleRepository.findByStartDate(startDate);
    }

    @Override
    public Collection<Schedule> getAllSchedules() {
        logger.debug("Getting all schedules");
        return scheduleRepository.findAll();
    }

    @Override
    public Long getScheduleCountByUser(User user) {
        return scheduleRepository.countByUser(user);
    }


    @Override
    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
