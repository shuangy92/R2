package com.worksap.stm2016.service.user;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.form.UserCreateForm;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Page<User> getAllUsers(String sort, String order, Integer page, Integer limit);

    Page<User> getAllUsersByNameContaining(String name, String sort, String order, Integer page, Integer limit);

    Page<User> getAllUsersByRole(Role name, String sort, String order, Integer page, Integer limit);

    Page<User> getAllUsersByStatus(UserStatus name, String sort, String order, Integer page, Integer limit);

    Long getUserCount();

    Long getUserCountByName(String name);

    Long getUserCountByRole(Role role);

    Long getUserCountByStatus(UserStatus status);

    User create(UserCreateForm form);

    User update(User user);
}