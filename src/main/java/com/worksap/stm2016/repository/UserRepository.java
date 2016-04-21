package com.worksap.stm2016.repository;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor {

    public Optional<User> findOneByEmail(String email);

    public Page<User> findByNameContainingIgnoreCase(Pageable pageable, String name);

    public Page<User> findByRole(Pageable pageable, Role role);

    public Page<User> findByStatus(Pageable pageable, UserStatus status);

    public Long countByName(String name);

    public Long countByRole(Role role);

    public Long countByStatus(UserStatus status);

}
