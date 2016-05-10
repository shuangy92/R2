package com.worksap.stm2016.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.message.OtherRequest;
import com.worksap.stm2016.domain.message.Request;
import com.worksap.stm2016.domain.message.StaffingRequest;
import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.enums.RequestStatus;
import com.worksap.stm2016.enums.RequestType;
import com.worksap.stm2016.repository.job.JobRepository;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.worksap.stm2016.specification.BasicSpecs.SortAndFilter;
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

    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getUserRequestList(Authentication authentication,
                                         @RequestParam(name = "sort") String sort,
                                         @RequestParam(name = "order") String order,
                                         @RequestParam(name = "limit") Integer limit,
                                         @RequestParam(name = "offset") Integer offset,
                                         @RequestParam(name = "filter", required = false) String filter) throws org.json.simple.parser.ParseException {
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        Specification userSpec = isValue("sender", user);

        Integer page = offset / limit;
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, limit, direction, sort);

        ArrayList<Specification> specs = new ArrayList<>();
        List<Request> requests = new ArrayList<>();
        Long count = Long.valueOf(0);

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                if (key.equals("type")) {
                    spec = isValue(key, RequestType.valueOf(search));
                } else if (key.equals("status")) {
                    spec = isValue(key, RequestStatus.valueOf(search));
                } else if (key.equals("sendDate")) {
                    spec = isValue(key, new Date(Long.parseLong(search)));
                } else { //key = title
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
            if (specs.size() == 1) {
                requests = requestRepository.findAll(where(userSpec).and(specs.get(0)), pageable).getContent();
                count = requestRepository.count(where(userSpec).and(specs.get(0)));
            } else if (specs.size() == 2) {
                requests = requestRepository.findAll(where(userSpec).and(specs.get(0)).and(specs.get(1)), pageable).getContent();
                count = requestRepository.count(where(userSpec).and(specs.get(0)).and(specs.get(1)));
            } else if (specs.size() == 3) {
                requests = requestRepository.findAll(where(userSpec).and(specs.get(0)).and(specs.get(1)).and(specs.get(2)), pageable).getContent();
                count = requestRepository.count(where(userSpec).and(specs.get(0)).and(specs.get(1)).and(specs.get(2)));
            }
        } else {
            requests = requestRepository.findAll(userSpec, pageable).getContent();
            count = requestRepository.count(userSpec);
        }

        JSONObject result = new JSONObject();
        result.put("rows", requests);
        result.put("total", count);

        return result;
    }

    @RequestMapping(value = "/hr", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getHRRequestList(Authentication authentication,
                                       @RequestParam(name = "sort") String sort,
                                       @RequestParam(name = "order") String order,
                                       @RequestParam(name = "limit") Integer limit,
                                       @RequestParam(name = "offset") Integer offset,
                                       @RequestParam(name = "filter", required = false) String filter) throws org.json.simple.parser.ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                if (key.equals("requestType")) {
                    spec = isValue(key, RequestType.valueOf(search));
                } else if (key.equals("status")) {
                    spec = isValue(key, RequestStatus.valueOf(search));
                } else if (key.equals("sendDate")) {
                    spec = isValue(key, new Date(Long.parseLong(search)));
                } else if (key.equals("sender")) {
                    spec = hasValue(key, "name", search);
                } else { // key = title
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
        }

        JSONObject result = SortAndFilter( sort,  order,  limit,  offset,  filter,  specs, requestRepository);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveOtherRequest(Authentication authentication,
                                 @RequestBody JSONObject requestJson) throws ParseException, IOException {
        Request request;
        ObjectMapper mapper = new ObjectMapper();
        switch (requestJson.get("requestType").toString()) {
            case ("STAFFING"):
                request = mapper.readValue(requestJson.toString(), StaffingRequest.class);
                break;
            default:
                request = mapper.readValue(requestJson.toString(), OtherRequest.class);
        }

        User sender = ((CurrentUser) authentication.getPrincipal()).getUser();
        request.setSender(sender);
        request.setSendDate(new Date());
        request.setDepartment(sender.getDepartment());

        requestRepository.save(request);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public String updateRequest(Authentication authentication,
                                @RequestBody JSONObject requestJson) throws org.springframework.expression.ParseException, IOException {
        Request request;
        ObjectMapper mapper = new ObjectMapper();
        switch (requestJson.get("requestType").toString()) {
            case ("STAFFING"):
                request = mapper.readValue(requestJson.toString(), StaffingRequest.class);
                break;
            default:
                request = mapper.readValue(requestJson.toString(), OtherRequest.class);
        }

        User replier = ((CurrentUser) authentication.getPrincipal()).getUser();
        request.setReplier(replier);
        request.setReplyDate(new Date());
        requestRepository.save(request);
        return "Your reply has been sent";
    }
}
