package com.worksap.stm2016.repository;

import com.worksap.stm2016.domain.FileProfile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileProfileRepository extends PagingAndSortingRepository<FileProfile, Long>,
        JpaSpecificationExecutor {

    FileProfile findOneByPath(String path);
}