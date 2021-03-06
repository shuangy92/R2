package com.worksap.stm2016.service.job;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.job.JobHistory;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.PayRate;
import com.worksap.stm2016.repository.job.ContractRepository;
import com.worksap.stm2016.repository.job.DepartmentRepository;
import com.worksap.stm2016.repository.job.JobHistoryRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import com.worksap.stm2016.util.DateUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class ContractService {

    private static final Logger logger = LoggerFactory.getLogger(ContractService.class);
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    JobHistoryRepository jobHistoryRepository;

    public Contract get(Long id){
        return contractRepository.findOne(id);
    }

    public JsonArrayResponse getList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {
        return getListHelper(sort, order, limit, offset, filter, Contract.class);
    }

    public JsonArrayResponse getJobHistoryList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {
        return getListHelper(sort, order, limit, offset, filter, JobHistory.class);
    }

    private<T> JsonArrayResponse getListHelper(String sort, String order, Integer limit, Integer offset, String filter, Class<T> tClass) throws ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                if (key.equals("user")) {
                    spec = hasValue(key, "name", search);
                } else if (key.equals("payRate")) {
                    spec = isValue(key, PayRate.valueOf(search));
                } else if (key.equals("department")) {
                    spec = isValue("job", "department", "name", search);
                } else if (key.equals("date")) {
                    Date from = DateUtil.parseDate(search.split("-")[0], "MM/dd/yyyy");
                    Date to = DateUtil.parseDate(search.split("-")[1], "MM/dd/yyyy");
                    spec = betweenDates("endDate", from, to);
                } else { // key = location
                    spec = isValue("job", "department", "location", search);
                }
                specs.add(spec);
            }
        }

        if (tClass == Contract.class) {
            return andFilter( sort,  order,  limit,  offset,  filter,  specs, contractRepository);
        } else {
            return andFilter( sort,  order,  limit,  offset,  filter,  specs, jobHistoryRepository);
        }
    }

    public Contract save(Contract contract){
        if (contract.getUser() == null || contract.getUser().getId() == null) {
            return null;
        }
        User user = userRepository.findOne(contract.getUser().getId());

        Contract oldContract = user.getContract();
        if (oldContract != null) {
            contractRepository.delete(oldContract);
            jobHistoryRepository.save(new JobHistory(oldContract));
        }
        try {
            contract = contractRepository.save(contract);
        } catch(Exception e){
            return null;
        }

        user.setDepartment(contract.getJob().getDepartment());
        //user.setActive(true);
        user.setContract(contract);
        userRepository.save(user);

        return contract;
    }

    public Contract update(Contract contract){
        try {
            return contractRepository.save(contract);
        } catch(Exception e){
            return null;
        }
    }

    public Long deleteList(ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                contractRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }
}
