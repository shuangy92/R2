package com.worksap.stm2016.repository;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.job.Department;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor {

    User findOneByEmail(String email);
    Iterable<User> findByDepartment(Department department);
    Iterable<User> findByNameContainingIgnoreCase(String name);
}
