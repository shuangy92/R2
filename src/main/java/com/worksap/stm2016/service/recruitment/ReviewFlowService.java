package com.worksap.stm2016.service.recruitment;

import com.worksap.stm2016.domain.review.ReviewFlow;
import com.worksap.stm2016.domain.review.ReviewRun;
import com.worksap.stm2016.repository.recruitment.ReviewFlowRepository;
import com.worksap.stm2016.repository.recruitment.ReviewResponseRepository;
import com.worksap.stm2016.repository.recruitment.ReviewRunRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.filterAnd;
import static com.worksap.stm2016.specification.BasicSpecs.hasValue;

@Service
public class ReviewFlowService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewFlowService.class);
    @Autowired
    ReviewFlowRepository reviewFlowRepository;
    @Autowired
    ReviewRunRepository reviewRunRepository;
    @Autowired
    ReviewResponseRepository reviewResponseRepository;

    public ReviewFlow get(Long id){
        return reviewFlowRepository.findOne(id);
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
                if (key.equals("author")) {
                    spec = hasValue(key, "name", search);
                } else { // key = title
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
        }

        JSONObject result = filterAnd( sort,  order,  limit,  offset,  filter,  specs, reviewFlowRepository);
        return result;
    }

    public ReviewFlow save(ReviewFlow reviewFlow){
        return reviewFlowRepository.save(reviewFlow);
    }

    public ReviewFlow update(ReviewFlow reviewFlow){
        return reviewFlowRepository.save(reviewFlow);
    }

    public void delete(Long id){
        reviewFlowRepository.delete(id);
    }

    public Long deleteList(ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                reviewFlowRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }

    public Long deleteRunList(Long flowId, ArrayList<Long> ids){
        ReviewFlow reviewFlow = this.get(flowId);
        for (Long runId: ids) {
            ReviewRun reviewRun = reviewRunRepository.findOne(runId);
            reviewFlow.removeRun(reviewRun);
            try {
                reviewRunRepository.delete(runId);
            } catch (Exception e) {
                return runId;
            }
        }
        return Long.valueOf(0);
    }
}
