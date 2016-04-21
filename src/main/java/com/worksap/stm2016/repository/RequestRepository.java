package com.worksap.stm2016.repository;


import com.worksap.stm2016.domain.Request.Request;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.RequestType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "request", path = "request")

public interface RequestRepository extends PagingAndSortingRepository<Request, Long>,
        JpaSpecificationExecutor {
    public Collection findBySenderAndType(User sender, RequestType type);
}
