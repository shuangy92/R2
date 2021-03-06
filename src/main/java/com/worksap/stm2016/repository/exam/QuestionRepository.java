package com.worksap.stm2016.repository.exam;


import com.worksap.stm2016.domain.exam.Question;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long>,
        JpaSpecificationExecutor {
}
