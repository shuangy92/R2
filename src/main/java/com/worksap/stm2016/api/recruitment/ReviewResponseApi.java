package com.worksap.stm2016.api.recruitment;

import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.service.recruitment.ReviewResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/review_response")
public class ReviewResponseApi {

    private static final Logger logger = LoggerFactory.getLogger(ReviewResponseApi.class);
    @Autowired
    ReviewResponseService reviewResponseService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @RequestMapping(method = RequestMethod.PUT)
    public ReviewResponse update(@RequestBody ReviewResponse reviewResponse){
        return reviewResponseService.update(reviewResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    @RequestMapping(value="status_list/{ids}", method = RequestMethod.GET)
    public List getStatusList(@PathVariable List<Integer> ids){
        return reviewResponseService.getStatusList(ids);
    }
}
