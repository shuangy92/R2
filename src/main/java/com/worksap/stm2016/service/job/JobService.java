package com.worksap.stm2016.service.job;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.job.Job;
import com.worksap.stm2016.repository.job.JobRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobService.class);
    @Autowired
    JobRepository jobRepository;

    public Job get(Long id){
        return jobRepository.findOne(id);
    }

    public JsonArrayResponse getList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {

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
                    spec = isValue(key, "name", search);
                } else if (key.equals("location")) {
                    spec = isValue("department", "location", search);
                } else if (key.equals("title")){
                    spec = hasValue(key, search);
                } else { // key = jobCategory
                    spec = isValue(key, search);
                }
                specs.add(spec);
            }
        }

        return andFilter( sort,  order,  limit,  offset,  filter,  specs, jobRepository);
    }

    public void save(Job job){
        jobRepository.save(job);
    }

    public void update(Job job){
        jobRepository.save(job);
    }

    public Long deleteList(ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                jobRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }
}
