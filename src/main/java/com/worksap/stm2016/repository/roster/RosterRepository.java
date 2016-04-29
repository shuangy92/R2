package com.worksap.stm2016.repository.roster;


import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.roster.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Long> {
    Optional<Roster> findOneByAuthorAndStartDate(User author, Integer startDate);

    Collection<Roster> findByAuthor(User author);

    Collection<Roster> findByStartDate(Integer startDate);
}
