package com.worksap.stm2016.api.job;

import com.worksap.stm2016.domain.job.Job;
import com.worksap.stm2016.service.job.JobService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/job")
@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
public class JobApi {

    private static final Logger logger = LoggerFactory.getLogger(JobApi.class);
    @Autowired
    JobService jobService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Job get(@PathVariable("id") Long id){
        return jobService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                                  @RequestParam(name = "order") String order,
                                  @RequestParam(name = "limit") Integer limit,
                                  @RequestParam(name = "offset") Integer offset,
                                  @RequestParam(name = "filter", required = false) String filter) throws ParseException {

       return jobService.getList(sort, order, limit, offset, filter);
    }
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Job job){
        jobService.save(job);
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Job job){
        jobService.update(job);
    }

    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteList(@RequestBody ArrayList<Long> ids){
        jobService.deleteList(ids);
    }
}
