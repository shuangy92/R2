package com.worksap.stm2016.service.job;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.job.DepartmentRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@Service
public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;

    public Department get(Long id){
        return departmentRepository.findOne(id);
    }


    public Iterable<Department> getAll() {
        return departmentRepository.findAllByOrderByNameAsc();
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
                } else if (key.equals("manager")) {
                    spec = hasValue(key, "name", search);
                } else if (key.equals("name")) {
                    spec = hasValue(key, search);
                } else { // key = location
                    spec = isValue(key, search);
                }
                specs.add(spec);
            }
        }
        return andFilter(sort, order, limit, offset, filter, specs, departmentRepository);
    }

    public Department save(Department department) {
        try {
            return departmentRepository.save(department);
        } catch (Exception e) {
            return null;
        }
    }

    public Department update(Department department) {
        User prevManager = departmentRepository.findOne(department.getId()).getManager();
        if (prevManager != null) {
            if (prevManager.getRole() != Role.ADMIN) {
                prevManager.setRole(Role.EMPLOYEE);
                userRepository.save(prevManager);
            }
        }
        if (department.getManager() != null) {
            User newManager = userRepository.findOne(department.getManager().getId());
            if (newManager.getRole() != Role.ADMIN) {
                newManager.setRole(Role.MANAGER);
                userRepository.save(newManager);
            }
        }
        try {
            return departmentRepository.save(department);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Long deleteList(ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                departmentRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }
}
