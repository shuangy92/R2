package com.worksap.stm2016.repository.job;


import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.job.JobCategory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long>,
        JpaSpecificationExecutor {

    public Iterable<Department> findAllByOrderByNameAsc();
    public Department findOneByName(String name);
}
