package com.worksap.stm2016.repository.message;


import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.message.Request;
import com.worksap.stm2016.domain.message.StaffingRequest;
import com.worksap.stm2016.enums.RequestType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StaffingRequestRepository extends PagingAndSortingRepository<StaffingRequest, Long>,
        JpaSpecificationExecutor {
    //public Collection findBySenderAndType(User sender, RequestType type);
}
