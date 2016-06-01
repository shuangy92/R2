package com.worksap.stm2016.repository.exam;


import com.worksap.stm2016.domain.Country;
import com.worksap.stm2016.domain.exam.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
