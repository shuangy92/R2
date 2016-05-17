package com.worksap.stm2016.api.job;

import com.worksap.stm2016.domain.job.JobCategory;
import com.worksap.stm2016.repository.job.JobCategoryRepository;
import com.worksap.stm2016.service.job.JobCategoryService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.SortAndFilter;
import static com.worksap.stm2016.specification.BasicSpecs.hasValue;

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
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                                         @RequestParam(name = "order") String order,
                                         @RequestParam(name = "limit") Integer limit,
                                         @RequestParam(name = "offset") Integer offset,
                                         @RequestParam(name = "filter", required = false) String filter) throws ParseException {
        return jobCategoryService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody JobCategory jobCategory) throws ParseException {
        jobCategoryService.save(jobCategory);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody JobCategory jobCategory) throws ParseException {
        jobCategoryService.update(jobCategory);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteList(@RequestBody ArrayList<Long> ids){
        jobCategoryService.deleteList(ids);
    }
}
