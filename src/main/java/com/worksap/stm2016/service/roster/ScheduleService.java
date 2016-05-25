package com.worksap.stm2016.service.roster;

import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.domain.roster.Schedule;
import com.worksap.stm2016.repository.roster.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Optional<Schedule> getScheduleById(long id) {
        logger.debug("Getting schedule by id={}", id);
        return Optional.ofNullable(scheduleRepository.findOne(id));
    }

    public Optional<Schedule> getScheduleByUserAndStartDate(User user, Integer startDate) {
        return scheduleRepository.findOneByUserAndStartDate(user, startDate);
    }

    public Collection<Schedule> getAllSchedulesByUser(User user) {
        logger.debug("Getting schedules by user={}", user);
        return scheduleRepository.findByUser(user);
    }

    public Collection<Schedule> getAllSchedulesByStartDate(Integer startDate) {
        return scheduleRepository.findByStartDate(startDate);
    }

    public Collection<Schedule> getAllSchedules() {
        logger.debug("Getting all schedules");
        return scheduleRepository.findAll();
    }

    public Long getScheduleCountByUser(User user) {
        return scheduleRepository.countByUser(user);
    }


    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
