package com.worksap.stm2016.repository;


import com.worksap.stm2016.domain.Contract;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.message.Request;
import com.worksap.stm2016.enums.RequestType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>,
        JpaSpecificationExecutor {
}
