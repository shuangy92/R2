package com.worksap.stm2016.repository.user;

import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor {

    User findOneByEmail(String email);

    Iterable<User> findByDepartment(Department department);

    Iterable<User> findByNameContainingIgnoreCaseAndRole(String name, Role role);
}
