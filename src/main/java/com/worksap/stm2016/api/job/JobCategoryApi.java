package com.worksap.stm2016.api.job;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.job.JobCategory;
import com.worksap.stm2016.service.job.JobCategoryService;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.worksap.stm2016.api.util.JsonResponse.deletionResponse;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/job_category")
public class JobCategoryApi {

    private static final Logger logger = LoggerFactory.getLogger(JobCategoryApi.class);
    @Autowired
    JobCategoryService jobCategoryService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public JobCategory get(@PathVariable("id") Long id){
        return jobCategoryService.get(id);
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public Iterable<JobCategory> getAll() throws ParseException {
        return jobCategoryService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonArrayResponse getList(@RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter) throws ParseException {
        return jobCategoryService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public JobCategory save(@RequestBody JobCategory jobCategory) {
        return jobCategoryService.save(jobCategory);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public JobCategory update(@RequestBody JobCategory jobCategory) {
        return jobCategoryService.save(jobCategory);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse deleteList(@RequestBody ArrayList<Long> ids){
        Long result = jobCategoryService.deleteList(ids);
        return deletionResponse(null, result);
    }
}
