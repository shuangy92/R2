package com.worksap.stm2016.service.currentuser;

import com.worksap.stm2016.domain.util.CurrentUser;
import com.worksap.stm2016.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        //LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER || currentUser.getId().equals(userId))
                && currentUser.getUser().isActive() == true;
    }

    @Override
    public boolean canAccessUserResigned(CurrentUser currentUser, Long userId) {
        //LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER || currentUser.getId().equals(userId));
    }

    @Override
    public boolean permitAll(CurrentUser currentUser) {
        return currentUser != null;
    }

}