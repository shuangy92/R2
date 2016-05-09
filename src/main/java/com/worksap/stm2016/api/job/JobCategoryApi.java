package com.worksap.stm2016.api.job;

import com.worksap.stm2016.domain.Job;
import com.worksap.stm2016.repository.job.JobRepository;
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
import static com.worksap.stm2016.specification.BasicSpecs.isValue;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/job")
@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
public class JobCategoryApi {

    private static final Logger logger = LoggerFactory.getLogger(JobCategoryApi.class);
    @Autowired
    JobRepository jobRepository;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Job get(@PathVariable("id") Long id){
        return jobRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getJobList(@RequestParam(name = "sort") String sort,
                                  @RequestParam(name = "order") String order,
                                  @RequestParam(name = "limit") Integer limit,
                                  @RequestParam(name = "offset") Integer offset,
                                  @RequestParam(name = "filter", required = false) String filter) throws ParseException {

       ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                if (key.equals("id")) {
                    spec = isValue(key, Long.parseLong(search));
                } else if (key.equals("department")) {
                    spec = hasValue(key, "name", search);
                } else if (key.equals("location")) {
                    spec = hasValue("department", "location", search);
                } else { //key = title
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
        }

        JSONObject result = SortAndFilter( sort,  order,  limit,  offset,  filter,  specs, jobRepository);
        return result;
    }
}
