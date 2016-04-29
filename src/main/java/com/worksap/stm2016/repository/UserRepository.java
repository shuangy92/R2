package com.worksap.stm2016.repository;

import com.worksap.stm2016.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor {

    /*@Override
    User save(User user);

    @Override
    void delete(Long id);*/

    @Override
    User findOne(Long id);

    @Override
    Page<User> findAll();

    Optional<User> findOneByEmail(String email);

}
