package com.worksap.stm2016.api;

import com.worksap.stm2016.domain.JobPost;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.util.CurrentUser;
import com.worksap.stm2016.repository.JobPostRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/api/job_post")
public class JobPostApi {

    private static final Logger logger = LoggerFactory.getLogger(JobPostApi.class);
    @Autowired
    JobPostRepository jobPostRepository;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public JobPost get(@PathVariable("id") Long id){
        return jobPostRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getJobPostList(Authentication authentication,
                                     @RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter
    ) throws org.json.simple.parser.ParseException {
        Integer page = offset / limit;
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, limit, direction, sort);

        ArrayList<Specification> specs = new ArrayList<>();
        List<JobPost> jobPosts = new ArrayList<>();
        Long count = Long.valueOf(0);

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                if (key.equals("job")) {
                    spec = isValue(key, "id", Long.parseLong(search));
                } else if (key.equals("published")) {
                    Boolean s = search.equals("true");
                    spec = isValue(key, s);
                } else { // key = title
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
            if (specs.size() == 1) {
                jobPosts = jobPostRepository.findAll(specs.get(0), pageable).getContent();
                count = jobPostRepository.count(specs.get(0));
            } else if (specs.size() == 2) {
                jobPosts = jobPostRepository.findAll(where(specs.get(0)).and(specs.get(1)), pageable).getContent();
                count = jobPostRepository.count(where(specs.get(0)).and(specs.get(1)));
            } else if (specs.size() == 3) {
                jobPosts = jobPostRepository.findAll(where(specs.get(0)).and(specs.get(1)).and(specs.get(2)), pageable).getContent();
                count = jobPostRepository.count(where(specs.get(0)).and(specs.get(1)).and(specs.get(2)));
            }
        } else {
            jobPosts = jobPostRepository.findAll(pageable).getContent();
            count = jobPostRepository.count();
        }

        JSONObject result = new JSONObject();
        result.put("rows", jobPosts);
        result.put("total", count);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public JobPost save(Authentication authentication,
                        @RequestBody JobPost post){
        User author = ((CurrentUser) authentication.getPrincipal()).getUser();

        post.setAuthor(author);
        return jobPostRepository.save(post);
    }
}
