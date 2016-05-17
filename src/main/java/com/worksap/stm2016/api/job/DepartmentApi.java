package com.worksap.stm2016.api.job;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.job.JobCategory;
import com.worksap.stm2016.domain.message.StaffingRequest;
import com.worksap.stm2016.repository.job.DepartmentRepository;
import com.worksap.stm2016.service.job.DepartmentService;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentApi {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentApi.class);
    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public JSONObject get(@PathVariable("id") Long id){
        return departmentService.getFull(id);
    }

    /*@RequestMapping(value="/{id}/manager", method = RequestMethod.GET)
    public User getManager(@PathVariable("id") Long id){
        return departmentService.getManager(id);
    }*/

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public Iterable<Department> getAll() throws ParseException {
        return departmentService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                                         @RequestParam(name = "order") String order,
                                         @RequestParam(name = "limit") Integer limit,
                                         @RequestParam(name = "offset") Integer offset,
                                         @RequestParam(name = "filter", required = false) String filter) throws ParseException {
        return departmentService.getList(sort, order, limit, offset, filter);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Department department) throws ParseException {
        departmentService.save(department);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Department department) throws ParseException {
        departmentService.update(department);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteList(@RequestBody ArrayList<Long> ids){
        departmentService.deleteList(ids);
    }
}
