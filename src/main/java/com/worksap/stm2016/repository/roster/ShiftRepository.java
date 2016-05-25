package com.worksap.stm2016.repository.roster;

import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.domain.roster.Roster;
import com.worksap.stm2016.domain.roster.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    Collection<Shift> findByRosterOrderByDateAscStartTimeAsc(Roster roster); //someone's roster(date&time) detail

    Collection<Shift> findByDate(Integer date); //rosters(user&time) for one day

    Collection<Shift> findByDateAndUserOrderByStartTimeAsc(Integer date, User user); //rosters(user&time) for one day

    Optional<Shift> findOneByRosterAndDate(Roster roster, Integer date); //roster for a user for a day

}
