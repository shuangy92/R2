package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.repository.recruitment.ReviewRunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class ReviewRunService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewRunService.class);
    @Autowired
    ReviewRunRepository reviewRunRepository;

    public ReviewRun get(Long id){
        return reviewRunRepository.findOne(id);
    }

    public ReviewRun save(ReviewRun reviewRun){
        return reviewRunRepository.save(reviewRun);
    }

    public ReviewRun update(ReviewRun reviewRun){
        return reviewRunRepository.save(reviewRun);
    }

    public void delete(Long id){
        reviewRunRepository.delete(id);
    }

    public Long deleteList(@RequestBody ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                reviewRunRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }
}
