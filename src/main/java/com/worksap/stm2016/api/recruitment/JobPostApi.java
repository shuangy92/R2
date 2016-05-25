package com.worksap.stm2016.api.recruitment;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.service.recruitment.JobPostService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.worksap.stm2016.api.util.JsonResponse.deletionResponse;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/job_post")
public class JobPostApi {

    private static final Logger logger = LoggerFactory.getLogger(JobPostApi.class);
    @Autowired
    JobPostService jobPostService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public JobPost get(@PathVariable("id") Long id){
        return jobPostService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                              @RequestParam(name = "order") String order,
                              @RequestParam(name = "limit") Integer limit,
                              @RequestParam(name = "offset") Integer offset,
                              @RequestParam(name = "filter", required = false) String filter
    ) throws org.json.simple.parser.ParseException {

        return jobPostService.getList(sort, order, limit, offset, filter);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public JobPost save(@RequestBody JobPost post){
        return jobPostService.save(post);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public JobPost update(@RequestBody JobPost post){
        return jobPostService.update(post);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse deleteList(@RequestBody ArrayList<Long> ids){
        Long result = jobPostService.deleteList(ids);
        return deletionResponse(null, result);
    }
}
