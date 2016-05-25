package com.worksap.stm2016.repository.message;


import com.worksap.stm2016.domain.message.Email;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.domain.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long>,
        JpaSpecificationExecutor {
}
