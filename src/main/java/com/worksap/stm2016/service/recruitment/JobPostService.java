package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.job.Job;
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

import java.util.*;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class JobPostService {

    private static final Logger logger = LoggerFactory.getLogger(JobPostService.class);
    @Autowired
    JobPostRepository jobPostRepository;
    @Autowired
    JobCategoryRepository jobCategoryRepository;
    @Autowired
    ReviewFlowService reviewFlowService;
    @Autowired
    JobApplicationRepository jobApplicationRepository;

    public JobPost get(Long id){
        return jobPostRepository.findOne(id);
    }

    public JsonArrayResponse getList(String sort, String order, Integer limit, Integer offset, String filter
    ) throws org.json.simple.parser.ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec = null;
                switch (key) {
                    case "department":
                        spec = isValue(key, "name", search);
                        break;
                    case "location":
                        spec = isValue("department", "location", search);
                        break;
                    case "author":
                        spec = hasValue(key, "name", search);
                        break;
                    case "jobCategory":
                        JobCategory jobCategory = jobCategoryRepository.findOneByName(search);
                        spec = isValue("job", "jobCategory", jobCategory);
                        break;
                    case "published":
                    case "open":
                        Boolean s = search.equals("true");
                        spec = isValue(key, s);
                        break;
                    case "title":
                        spec = hasValue(key, search);
                        break;
                }
                specs.add(spec);
            }
        }

        return andFilter( sort,  order,  limit,  offset,  filter,  specs, jobPostRepository);
    }

    public JobPost save(JobPost post){
        return jobPostRepository.save(post);
    }

    public JobPost update(JobPost post){
        return jobPostRepository.save(post);
    }

    public void delete(Long id){
        jobPostRepository.delete(id);
    }

    public Long deleteList(@RequestBody ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                jobPostRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }

    public Map<JobApplication.JobApplicationStatus, Integer> getStatistics(JobPost jobPost) {
        Map<JobApplication.JobApplicationStatus, Integer> statusCount = new HashMap<>();
        Collection<JobApplication> jobApplications = jobApplicationRepository.findByJobPost(jobPost);
        for (JobApplication jobApplication : jobApplications) {
            JobApplication.JobApplicationStatus status = jobApplication.getStatus();
            if (statusCount.containsKey(status)) {
                int count = statusCount.get(status);
                statusCount.put(status, count + 1);
            } else {
                statusCount.put(status, 1);
            }
        }
        return statusCount;
    }
}
