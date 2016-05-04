package com.worksap.stm2016.api;


import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.message.Request;
import com.worksap.stm2016.domain.message.StaffingRequest;
import com.worksap.stm2016.domain.util.CurrentUser;
import com.worksap.stm2016.repository.JobRepository;
import com.worksap.stm2016.repository.UserRepository;
import com.worksap.stm2016.repository.message.RequestRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.worksap.stm2016.specification.BasicSpecs.hasValue;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/request")
//@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
public class RequestApi {

    private static final Logger logger = LoggerFactory.getLogger(RequestApi.class);
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Request get(@PathVariable("id") Long id){
        return (Request) requestRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void getRequestList(Authentication authentication, @RequestBody StaffingRequest request) throws ParseException {

        User sender = ((CurrentUser) authentication.getPrincipal()).getUser();
        request.setSender(sender);
        request.setSendDate(new Date());

        Request r = (Request) requestRepository.save(request);
        logger.debug("{}", requestRepository.findOne(r.getId()));
    }

    @RequestMapping(value="/staffing", method = RequestMethod.POST)
    public void saveStaffingRequest(Authentication authentication, @RequestBody StaffingRequest request) throws ParseException {

        User sender = ((CurrentUser) authentication.getPrincipal()).getUser();
        request.setSender(sender);
        request.setSendDate(new Date());

        Request r = (Request) requestRepository.save(request);
        logger.debug("{}", requestRepository.findOne(r.getId()));
    }
}
