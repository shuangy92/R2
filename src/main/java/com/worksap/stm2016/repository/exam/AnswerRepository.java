package com.worksap.stm2016.repository.exam;


import com.worksap.stm2016.domain.exam.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
