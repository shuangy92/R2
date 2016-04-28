package com.worksap.stm2016.service.currentuser;

import com.worksap.stm2016.domain.util.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

    boolean canAccessUserResigned(CurrentUser currentUser, Long userId);

    boolean permitAll(CurrentUser currentUser);
}