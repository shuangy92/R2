package com.worksap.stm2016.service.user;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.UserProfile;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.form.UserCreateForm;
import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.repository.UserProfileRepository;
import com.worksap.stm2016.repository.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class UserProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile getProfile(Long id){
        UserProfile userProfile = userProfileRepository.findOne(id);
        userProfile.setName(userService.get(id).getName());
        return userProfile;
    }
    public void saveOrUpdateProfile(UserProfile userProfile){
        userProfileRepository.save(userProfile);
        User user = userService.get(userProfile.getId());
        user.setName(userProfile.getName());
        userService.update(user);
    }

    void delete(Long id){
        userProfileRepository.delete(id);
    }

}
