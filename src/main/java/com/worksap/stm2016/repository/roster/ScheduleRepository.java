package com.worksap.stm2016.repository.roster;


import com.worksap.stm2016.domain.roster.Schedule;
import com.worksap.stm2016.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    public Optional<Schedule> findOneByUserAndStartDate(User user, Integer startDate);

    public Collection<Schedule> findByUser(User user);

    public Collection<Schedule> findByStartDate(Integer startDate);

    public Long countByUser(User user);

}
