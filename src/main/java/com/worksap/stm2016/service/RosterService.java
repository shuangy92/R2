package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.roster.Roster;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.repository.roster.RosterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RosterService {

    private static final Logger logger = LoggerFactory.getLogger(RosterService.class);
    private final RosterRepository RosterRepository;

    @Autowired
    public RosterService(RosterRepository RosterRepository) {
        this.RosterRepository = RosterRepository;
    }

    public Optional<Roster> getRosterById(long id) {
        logger.debug("Getting Roster by id={}", id);
        return Optional.ofNullable(RosterRepository.findOne(id));
    }

    public Optional<Roster> getRosterByAuthorAndStartDate(User author, Integer startDate) {
        return RosterRepository.findOneByAuthorAndStartDate(author, startDate);
    }

    public Collection<Roster> getAllRostersByAuthor(User author) {
        return RosterRepository.findByAuthor(author);
    }

    public Collection<Roster> getAllRosters() {
        logger.debug("Getting all Rosters");
        return RosterRepository.findAll();
    }

    public Roster create(Roster Roster) {
        return RosterRepository.save(Roster);
    }
}
