package com.worksap.stm2016.repository.roster;


import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.roster.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findOneByUserAndStartDate(User user, Integer startDate);

    Collection<Schedule> findByUser(User user);

    Collection<Schedule> findByStartDate(Integer startDate);

    Long countByUser(User user);

}
