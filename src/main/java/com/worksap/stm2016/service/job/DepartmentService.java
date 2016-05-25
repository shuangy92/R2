package com.worksap.stm2016.service.job;

import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.job.DepartmentRepository;
import com.worksap.stm2016.service.user.UserService;
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
    UserService userService;

    public Department get(Long id){
        return departmentRepository.findOne(id);
    }

    public JSONObject getFull(Long id){
        Department department = departmentRepository.findOne(id);
        JSONObject result = new JSONObject();
        result.put("id", department.getId());
        result.put("name", department.getName());
        result.put("location", department.getLocation());
        if (department.getManagerId() != null) {
            result.put("manager", userService.get(department.getManagerId()));

        }
        return result;
    }

    public Iterable<Department> getAll() {
        return departmentRepository.findAllByOrderByNameAsc();
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
        JSONObject result = filterAnd(sort, order, limit, offset, filter, specs, departmentRepository);
        return result;
    }

    public Department save(Department department) throws ParseException {
        return departmentRepository.save(department);
    }

    public Department update(Department department) throws ParseException {
        Long original = departmentRepository.findOne(department.getId()).getManagerId();
        if (original != null) {
            User manager = userService.get(original);
            manager.setRole(Role.EMPLOYEE);
            userService.save(manager);
        }
        if (department.getManagerId() != null) {
            User manager = userService.get(department.getManagerId());
            manager.setRole(Role.MANAGER);
            userService.save(manager);
        }

        return departmentRepository.save(department);
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
