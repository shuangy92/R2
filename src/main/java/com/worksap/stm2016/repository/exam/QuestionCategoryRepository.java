package com.worksap.stm2016.repository.exam;


import com.worksap.stm2016.domain.exam.QuestionCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionCategoryRepository extends CrudRepository<QuestionCategory, Long> {
}
