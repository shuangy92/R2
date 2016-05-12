package com.worksap.stm2016.service.user;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.form.UserCreateForm;
import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return userRepository.findOne(id);
    }

    public User getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setName(form.getName());
        user.setStatus(form.getStatus());
        user.setActive(true);
        return userRepository.save(user);
    }

    public User register(UserRegisterForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user.getId());
    }
}
