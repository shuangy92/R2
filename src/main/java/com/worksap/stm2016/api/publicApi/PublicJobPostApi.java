package com.worksap.stm2016.api.publicApi;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.service.recruitment.JobPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/public/job_post")
public class PublicJobPostApi {

    private static final Logger logger = LoggerFactory.getLogger(PublicJobPostApi.class);
    @Autowired
    JobPostService jobPostService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public JobPost get(@PathVariable("id") Long id){
        return jobPostService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonArrayResponse getList(@RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter
    ) throws org.json.simple.parser.ParseException {

        return jobPostService.getList(sort, order, limit, offset, filter);
    }
}
