package com.worksap.stm2016.service.job;

import com.worksap.stm2016.domain.job.JobCategory;
import com.worksap.stm2016.repository.job.JobCategoryRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.SortAndFilter;
import static com.worksap.stm2016.specification.BasicSpecs.hasValue;

/**
 * Created by Shuang on 4/25/2016.
 */
@Service
public class JobCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(JobCategoryService.class);
    @Autowired
    JobCategoryRepository jobCategoryRepository;

    public JobCategory get(Long id){
        return jobCategoryRepository.findOne(id);
    }

    public Iterable<JobCategory> getAll() {
        return jobCategoryRepository.findAllByOrderByNameAsc();
    }

    public JSONObject getList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {
        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec = hasValue(key, search);
                specs.add(spec);
            }
        }
        JSONObject result = SortAndFilter(sort, order, limit, offset, filter, specs, jobCategoryRepository);
        return result;
    }

    public void save(JobCategory jobCategory) throws ParseException {
        jobCategoryRepository.save(jobCategory);
    }

    public void update(JobCategory jobCategory) throws ParseException {
        jobCategoryRepository.save(jobCategory);
    }

    public void delete(Long id){
        jobCategoryRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteList(ArrayList<Long> ids){
        for (Long id: ids) {
            jobCategoryRepository.delete(id);
        }
    }
}