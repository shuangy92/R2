package com.worksap.stm2016.repository;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, Long>,
        JpaSpecificationExecutor {
    public UserProfile findOneByUser(User user);
}