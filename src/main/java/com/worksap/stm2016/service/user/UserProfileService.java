package com.worksap.stm2016.service.user;

import com.worksap.stm2016.domain.user.UserProfile;
import com.worksap.stm2016.repository.user.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile get(Long id){
        return userProfileRepository.findOne(id);
    }

    public UserProfile update(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public Long delete(Long id){
        if (userProfileRepository.findOne(id) == null) {
            return Long.valueOf(-1); // Not exists
        }
        try {
            userProfileRepository.delete(id);
        } catch (Exception e) {
            return id; // Referenced
        }
        return Long.valueOf(0); // OK
    }
}
