package com.worksap.stm2016.repository.calendar;


import com.worksap.stm2016.domain.calendar.CalendarEvent;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long>,
        JpaSpecificationExecutor {
}
