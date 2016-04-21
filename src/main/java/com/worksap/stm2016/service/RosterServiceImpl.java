package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.Roster;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.repository.RosterRepository;
import com.worksap.stm2016.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RosterServiceImpl implements RosterService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final RosterRepository RosterRepository;

    @Autowired
    public RosterServiceImpl(RosterRepository RosterRepository) {
        this.RosterRepository = RosterRepository;
    }

    @Override
    public Optional<Roster> getRosterById(long id) {
        logger.debug("Getting Roster by id={}", id);
        return Optional.ofNullable(RosterRepository.findOne(id));
    }

    @Override
    public Optional<Roster> getRosterByAuthorAndStartDate(User author, Integer startDate) {
        return RosterRepository.findOneByAuthorAndStartDate(author, startDate);
    }

    @Override
    public Collection<Roster> getAllRostersByAuthor(User author) {
        return RosterRepository.findByAuthor(author);
    }

    @Override
    public Collection<Roster> getAllRosters() {
        logger.debug("Getting all Rosters");
        return RosterRepository.findAll();
    }

    @Override
    public Roster create(Roster Roster) {
        return RosterRepository.save(Roster);
    }
}
