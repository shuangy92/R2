package com.worksap.stm2016.api.recruitment;

import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.domain.review.ReviewFlow;
import com.worksap.stm2016.domain.review.ReviewResponse;
import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.service.recruitment.JobPostService;
import com.worksap.stm2016.service.recruitment.ReviewFlowService;
import com.worksap.stm2016.service.recruitment.ReviewResponseService;
import com.worksap.stm2016.service.recruitment.ReviewRunService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/review_flow")
public class ReviewFlowApi {

    private static final Logger logger = LoggerFactory.getLogger(ReviewFlowApi.class);
    @Autowired
    ReviewFlowService reviewFlowService;
    @Autowired
    ReviewRunService reviewRunService;
    @Autowired
    ReviewResponseService reviewResponseService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ReviewFlow get(@PathVariable("id") Long id){
        return reviewFlowService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                              @RequestParam(name = "order") String order,
                              @RequestParam(name = "limit") Integer limit,
                              @RequestParam(name = "offset") Integer offset,
                              @RequestParam(name = "filter", required = false) String filter
    ) throws org.json.simple.parser.ParseException {

        return reviewFlowService.getList(sort, order, limit, offset, filter);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ReviewFlow save(@RequestBody ReviewFlow reviewFlow){
        return reviewFlowService.save(reviewFlow);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public ReviewFlow update(@RequestBody ReviewFlow reviewFlow){
        return reviewFlowService.update(reviewFlow);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteList(@RequestBody ArrayList<Long> ids){
        reviewFlowService.deleteList(ids);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public ReviewFlow saveRun(@RequestBody ReviewRun reviewRun){
        reviewRun = reviewRunService.save(reviewRun);
        ReviewFlow reviewFlow = reviewRun.getReviewFlow();
        reviewFlow.addRun(reviewRun);
        reviewFlow =  reviewFlowService.save(reviewFlow);
        return reviewFlow;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRunList(@PathVariable Long id, @RequestBody ArrayList<Long> ids){
        ReviewFlow reviewFlow = reviewFlowService.get(id);
        for (Long runId: ids) {
            ReviewRun reviewRun = reviewRunService.get(runId);
            reviewFlow.removeRun(reviewRun);
            reviewRunService.delete(runId);
        }
    }
}
