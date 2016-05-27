package com.worksap.stm2016.api.recruitment;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.review.ReviewFlow;
import com.worksap.stm2016.domain.review.ReviewRun;
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
import java.util.Collections;
import java.util.List;

import static com.worksap.stm2016.api.util.JsonResponse.deletionResponse;

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
        ReviewFlow reviewFlow = reviewFlowService.get(id);
        List<ReviewRun> runs = reviewFlow.getRuns();
        Collections.sort(runs);
        reviewFlow.setRuns(runs);
        return reviewFlow;
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
        List<ReviewRun> runs = reviewFlow.getRuns();
        for (short i = 0; i < runs.size(); i++) {
            reviewRunService.save(runs.get(i));
        }
        return reviewFlowService.save(reviewFlow);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse deleteList(@RequestBody ArrayList<Long> ids){
        Long result = reviewFlowService.deleteList(ids);
        if(result == 0) {
            return new JsonResponse(JsonResponse.ResponseStatus.OK, "Deleted");
        } else {
            return new JsonResponse(JsonResponse.ResponseStatus.ERROR, "Review flow " + result + " is referenced and cannot be deleted");
        }
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
    @RequestMapping(value = "/{runId}", method = RequestMethod.DELETE)
    public JsonResponse deleteRunList(@PathVariable Long runId, @RequestBody ArrayList<Long> ids){
        Long result = reviewFlowService.deleteRunList(runId, ids);
        return deletionResponse(null, result);
    }
}
