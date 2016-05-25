package com.worksap.stm2016.repository.user;

import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.domain.user.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

}
