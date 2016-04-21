package com.worksap.stm2016.service.user;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.form.UserCreateForm;
import com.worksap.stm2016.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }


    @Override
    public Page<User> getAllUsers(String sort, String order, Integer page, Integer limit) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Page<User> users = userRepository.findAll(new PageRequest(page, limit, direction, sort));
        return users;
    }

    @Override
    public Page<User> getAllUsersByNameContaining(String name, String sort, String order, Integer page, Integer limit) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        return userRepository.findByNameContainingIgnoreCase(new PageRequest(page, limit, direction, sort), name);
    }

    @Override
    public Page<User> getAllUsersByRole(Role role, String sort, String order, Integer page, Integer limit) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        return userRepository.findByRole(new PageRequest(page, limit, direction, sort), role);
    }

    @Override
    public Page<User> getAllUsersByStatus(UserStatus status, String sort, String order, Integer page, Integer limit) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        return userRepository.findByStatus(new PageRequest(page, limit, direction, sort), status);
    }

    @Override
    public Long getUserCount() {

        return userRepository.count();
    }

    @Override
    public Long getUserCountByName(String name) {
        return userRepository.countByName(name);
    }

    @Override
    public Long getUserCountByRole(Role role) {
        return userRepository.countByRole(role);
    }

    @Override
    public Long getUserCountByStatus(UserStatus status) {
        return userRepository.countByStatus(status);
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setName(form.getName());
        user.setStatus(form.getStatus());
        //user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

}
