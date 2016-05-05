package com.worksap.stm2016.api;


import com.worksap.stm2016.domain.Department;
import com.worksap.stm2016.repository.DepartmentRepository;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.worksap.stm2016.specification.BasicSpecs.hasValue;

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
