package com.worksap.stm2016.repository;

import com.worksap.stm2016.domain.FileProfile;
import com.worksap.stm2016.domain.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FileProfileRepository extends PagingAndSortingRepository<FileProfile, Long>,
        JpaSpecificationExecutor {

    FileProfile findOneByPath(String path);
    Collection<FileProfile> findAllByUserAndType(User user, FileProfile.FileType type);

}
