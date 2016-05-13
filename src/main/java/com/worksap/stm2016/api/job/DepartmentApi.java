package com.worksap.stm2016.api.job;


import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.repository.job.DepartmentRepository;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentApi {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentApi.class);
    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Department get(@PathVariable("id") Long id){
        return departmentRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Department> getDepartmentList() throws ParseException {
        return departmentRepository.findAll();
    }
}