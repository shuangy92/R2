package com.worksap.stm2016.repository;


import com.worksap.stm2016.domain.Roster;
import com.worksap.stm2016.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Long> {
    public Optional<Roster> findOneByAuthorAndStartDate(User author, Integer startDate);

    public Collection<Roster> findByAuthor(User author);

    public Collection<Roster> findByStartDate(Integer startDate);
}
