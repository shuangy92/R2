package com.worksap.stm2016.api.recruitment;

import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.service.recruitment.ReviewResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/review_flow")
public class ReviewFesponseApi {

    private static final Logger logger = LoggerFactory.getLogger(ReviewFesponseApi.class);
    @Autowired
    ReviewResponseService reviewResponseService;

    @RequestMapping(value = "{jobApplicationId}", method = RequestMethod.PUT)
    public ReviewResponse update(@PathVariable Long jobApplicationId, @RequestBody ReviewResponse reviewResponse){
        return reviewResponseService.updateResponseOfJobApplication(jobApplicationId, reviewResponse);
    }
}
