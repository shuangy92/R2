package com.worksap.stm2016.service.job;

import com.worksap.stm2016.domain.Contract;
import com.worksap.stm2016.domain.job.Job;
import com.worksap.stm2016.repository.ContractRepository;
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
public class ContractService {

    private static final Logger logger = LoggerFactory.getLogger(ContractService.class);
    @Autowired
    ContractRepository contractRepository;

    public Contract get(Long id){
        return contractRepository.findOne(id);
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

        JSONObject result = SortAndFilter( sort,  order,  limit,  offset,  filter,  specs, contractRepository);
        return result;
    }

    public Contract save(Contract contract){
        return contractRepository.save(contract);
    }

    public Contract update(Contract contract){
        return contractRepository.save(contract);
    }

    public void delete(Long id){
        contractRepository.delete(id);
    }
    public void deleteList(ArrayList<Long> ids){
        for (Long id: ids) {
            contractRepository.delete(id);
        }
    }
}
