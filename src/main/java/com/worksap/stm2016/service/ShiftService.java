package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.roster.Roster;
import com.worksap.stm2016.domain.roster.Shift;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.repository.roster.ShiftRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ShiftService {

    private static final Logger logger = LoggerFactory.getLogger(ShiftService.class);

    @Autowired
    private ShiftRepository shiftRepository;

    public Optional<Shift> getShiftById(long id) {
        return Optional.ofNullable(shiftRepository.findOne(id));
    }

    public Collection<Shift> getAllShiftByRoster(Roster roster) {
        return shiftRepository.findByRosterOrderByDateAscStartTimeAsc(roster);
    }

    public Collection<Shift> getAllShiftByDate(Integer date) {
        // TODO Auto-generated method stub
        //return shiftRepository.findByDate();
        return null;
    }

    public Collection<Shift> getAllShiftByDateAndUser(Integer date, User user) {
        return shiftRepository.findByDateAndUserOrderByStartTimeAsc(date, user);
    }

    public Optional<Shift> getShiftByRosterAndDate(Roster roster, Integer date) {
        // TODO Auto-generated method stub
        return null;
    }

    public Shift createOrUpdate(Shift shift) {
        Optional<Shift> oldTimeSlot = this.getShiftById(shift.getId());
        if (oldTimeSlot.isPresent()) {
            if (shift.getStartTime() == null) {
                shift.setStartTime(oldTimeSlot.get().getStartTime());
            }
            if (shift.getEndTime() == null) {
                shift.setEndTime(oldTimeSlot.get().getEndTime());
            }
            if (shift.getUser() == null) {
                shift.setUser(oldTimeSlot.get().getUser());
            }
            return shiftRepository.save(shift);
        } else {
            return shiftRepository.save(shift);
        }
    }
}
