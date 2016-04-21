package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.Roster;
import com.worksap.stm2016.domain.Shift;
import com.worksap.stm2016.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface ShiftService {

    Optional<Shift> getShiftById(long id);

    Collection<Shift> getAllShiftByRoster(Roster roster);

    Collection<Shift> getAllShiftByDate(Integer data);

    Collection<Shift> getAllShiftByDateAndUser(Integer date, User user);

    Optional<Shift> getShiftByRosterAndDate(Roster roster, Integer date);

    Shift createOrUpdate(Shift shift);
}