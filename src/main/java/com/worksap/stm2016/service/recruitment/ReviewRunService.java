package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.review.ReviewFlow;
import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.repository.recruitment.ReviewFlowRepository;
import com.worksap.stm2016.repository.recruitment.ReviewRunRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

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

    public void deleteList(@RequestBody ArrayList<Long> ids){
        for (Long id: ids) {
            reviewRunRepository.delete(id);
        }
    }
}
