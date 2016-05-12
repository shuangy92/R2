package com.worksap.stm2016.api.job;

import com.worksap.stm2016.domain.job.JobCategory;
import com.worksap.stm2016.domain.job.JobPost;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.repository.job.JobCategoryRepository;
import com.worksap.stm2016.repository.job.JobPostRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.worksap.stm2016.specification.BasicSpecs.SortAndFilter;
import static com.worksap.stm2016.specification.BasicSpecs.hasValue;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/job_post")
public class JobPostApi {

    private static final Logger logger = LoggerFactory.getLogger(JobPostApi.class);
    @Autowired
    JobPostRepository jobPostRepository;
    @Autowired
    JobCategoryRepository jobCategoryRepository;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public JobPost get(@PathVariable("id") Long id){
        return jobPostRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getJobPostList(@RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter
    ) throws org.json.simple.parser.ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                if (key.equals("department")) {
                    spec = isValue(key, "name", search);
                } else if (key.equals("location")) {
                    spec = isValue("department", "location", search);
                } else if (key.equals("author")) {
                    spec = hasValue(key, "name", search);
                } else if (key.equals("published")) {
                    Boolean s = search.equals("true");
                    spec = isValue(key, s);
                } else if (key.equals("jobCategory")) {
                    JobCategory jobCategory = jobCategoryRepository.findOneByName(search);
                    spec = isValue("job", "jobCategory", jobCategory);
                } else { // key = title
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
        }

        JSONObject result = SortAndFilter( sort,  order,  limit,  offset,  filter,  specs, jobPostRepository);
        return result;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public JobPost save(@RequestBody JobPost post){
        return jobPostRepository.save(post);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public JobPost update(@RequestBody JobPost post){
        return jobPostRepository.save(post);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestBody ArrayList<Long> ids){
        for (Long id: ids) {
            jobPostRepository.delete(id);
        }
    }
}
