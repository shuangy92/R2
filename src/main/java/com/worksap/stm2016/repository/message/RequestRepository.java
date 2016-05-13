package com.worksap.stm2016.repository.message;


import com.worksap.stm2016.domain.message.Request;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository<T extends Request> extends PagingAndSortingRepository<Request, Long>,
        JpaSpecificationExecutor {
}
