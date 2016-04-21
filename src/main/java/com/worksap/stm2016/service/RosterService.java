package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.Roster;
import com.worksap.stm2016.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface RosterService {
    Optional<Roster> getRosterById(long id);

    Optional<Roster> getRosterByAuthorAndStartDate(User author, Integer startDate);

    Collection<Roster> getAllRostersByAuthor(User author);

    Collection<Roster> getAllRosters();

    Roster create(Roster Roster);
}