package com.worksap.stm2016.api;

import com.worksap.stm2016.domain.Job;
import com.worksap.stm2016.repository.JobRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.worksap.stm2016.specification.BasicSpecs.hasValue;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/job")
@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
public class JobApi {

    private static final Logger logger = LoggerFactory.getLogger(JobApi.class);
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

        Integer page = offset / limit;
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, limit, direction, sort);

        ArrayList<Specification> specs = new ArrayList<>();
        List<Job> jobs;
        Long count = Long.valueOf(0);

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
                } else { //key = title
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
            if (specs.size() == 1) {
                jobs = jobRepository.findAll(specs.get(0), pageable).getContent();
                count = jobRepository.count(specs.get(0));
            } else { // size = 2
                jobs = jobRepository.findAll(where(specs.get(0)).and(specs.get(1)), pageable).getContent();
                count = jobRepository.count(where(specs.get(0)).and(specs.get(1)));
            }
        } else {
            jobs = jobRepository.findAll(pageable).getContent();
            count = jobRepository.count();
        }

        JSONObject result = new JSONObject();
        result.put("rows", jobs);
        result.put("total", count);

        return result;
    }
}
