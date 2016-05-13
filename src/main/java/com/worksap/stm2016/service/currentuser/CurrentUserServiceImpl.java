package com.worksap.stm2016.service.currentuser;

import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.message.Request;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.message.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Autowired
    RequestRepository requestRepository;

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
    public boolean canAccessRequest(CurrentUser currentUser, Long requestId) {
        Request request = (Request) requestRepository.findOne(requestId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || request.getSender() == currentUser.getUser() || request.getReplier() == currentUser.getUser());
    }

    @Override
    public boolean hasLoggedIn(CurrentUser currentUser) {
        return currentUser != null;
    }

}