package com.worksap.stm2016.service.currentuser;

import com.worksap.stm2016.audit.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

    boolean canAccessRequest(CurrentUser currentUser, Long requestId);

    boolean hasLoggedIn(CurrentUser currentUser);
}