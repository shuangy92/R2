package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.job.JobCategory;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.recruitment.JobPost;
import com.worksap.stm2016.repository.job.JobCategoryRepository;
import com.worksap.stm2016.repository.recruitment.JobApplicationRepository;
import com.worksap.stm2016.repository.recruitment.JobPostRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class JobApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationService.class);
    @Autowired
    JobApplicationRepository jobApplicationRepository;

    public JobApplication get(Long id){
        return jobApplicationRepository.findOne(id);
    }

    public JSONObject getList(String sort, String order, Integer limit, Integer offset, String filter
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
                    spec = isValue("jobPost", "department", "name", search);
                } else if (key.equals("location")) {
                    spec = isValue("jobPost", "department", "location", search);
                } else if (key.equals("title")) {
                    spec = hasValue("jobPost", "title", search);
                } else if (key.equals("jobPostId")) {
                    spec = isValue("jobPost", "id", Long.parseLong(search));
                } else if (key.equals("status")) {
                    spec = isValue(key, JobApplication.JobApplicationStatus.valueOf(search));
                } else if (key.equals("uid")) {
                    spec = isValue("applicant", "id", Long.parseLong(search));
                } else {
                    spec = isValue("applicant", "id", Long.parseLong(search));
                }
                specs.add(spec);
            }
        }

        JSONObject result = SortAndFilter( sort,  order,  limit,  offset,  filter,  specs, jobApplicationRepository);
        return result;
    }

    public JobApplication getByJobPostAndApplicant(JobPost jobPost, User applicant) {
        return jobApplicationRepository.findOneByJobPostAndApplicant(jobPost, applicant);
    }
    public JobApplication save(JobApplication application){
        return jobApplicationRepository.save(application);
    }

    public JobApplication update(JobApplication application){
        return jobApplicationRepository.save(application);
    }

    public void delete(Long id){
        jobApplicationRepository.delete(id);
    }

    public void delete(JobApplication jobApplication){
        jobApplicationRepository.delete(jobApplication);
    }

    public void deleteList(@RequestBody ArrayList<Long> ids){
        for (Long id: ids) {
            jobApplicationRepository.delete(id);
        }
    }
}
