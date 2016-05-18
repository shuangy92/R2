package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.repository.recruitment.ReviewResponseRepository;
import com.worksap.stm2016.repository.recruitment.ReviewRunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class ReviewResponseService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewResponseService.class);
    @Autowired
    ReviewResponseRepository reviewResponseRepository;

    public ReviewResponse get(Long id){
        return reviewResponseRepository.findOne(id);
    }

    public ReviewResponse save(ReviewResponse reviewResponse){
        return reviewResponseRepository.save(reviewResponse);
    }

    public ReviewResponse update(ReviewResponse reviewResponse){
        return reviewResponseRepository.save(reviewResponse);
    }

    public void delete(Long id){
        reviewResponseRepository.delete(id);
    }

    public void deleteList(@RequestBody ArrayList<Long> ids){
        for (Long id: ids) {
            reviewResponseRepository.delete(id);
        }
    }
}
