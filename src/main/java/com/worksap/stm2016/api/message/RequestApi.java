package com.worksap.stm2016.api.message;


import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.message.Request;
import com.worksap.stm2016.repository.message.RequestRepository;
import com.worksap.stm2016.service.message.RequestService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    RequestService requestService;

    @PreAuthorize("@currentUserServiceImpl.hasLoggedIn(principal)")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Request get(@PathVariable("id") Long id) {
        return (Request) requestService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonArrayResponse getList(@RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter) throws org.json.simple.parser.ParseException {

        return requestService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Request save(@RequestBody JSONObject requestJson) throws IOException {

        return requestService.save(requestJson);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Request update(@RequestBody JSONObject requestJson) throws IOException {

        return requestService.update(requestJson);
    }
}
