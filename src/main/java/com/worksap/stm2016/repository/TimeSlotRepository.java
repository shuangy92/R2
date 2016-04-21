package com.worksap.stm2016.repository;


import com.worksap.stm2016.domain.Schedule;
import com.worksap.stm2016.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    public Collection<TimeSlot> findByScheduleOrderByDateAscStartTimeAsc(Schedule schedule); //someone's schedule(date&time) detail

    public Collection<TimeSlot> findByDate(Integer date); //schedules(user&time) for one day

    public Optional<TimeSlot> findOneByScheduleAndDate(Schedule schedule, Integer date); //schedule for a user for a day

    @Modifying
    @Query("select ts from TimeSlot ts where ts.date = :date and ts.startTime <= :startTime and ts.endTime >= :startTime")
    public Collection<TimeSlot> findByDateAndStartTime(@Param("date") Integer date,
                                                       @Param("startTime") String startTime);

    @Modifying
    @Query("select ts from TimeSlot ts where ts.date = :date and ts.startTime <= :endTime and ts.endTime >= :endTime")
    public Collection<TimeSlot> findByDateAndEndTime(@Param("date") Integer date,
                                                     @Param("endTime") String endTime);

    @Modifying
    @Query("select ts from TimeSlot ts where ts.date = :date and ts.startTime <= :startTime and ts.endTime >= :endTime")
    public Collection<TimeSlot> findByDateAndAvailableTime(@Param("date") Integer date,
                                                           @Param("startTime") String startTime,
                                                           @Param("endTime") String endTime);
}
