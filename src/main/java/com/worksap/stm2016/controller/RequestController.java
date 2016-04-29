package com.worksap.stm2016.controller;

import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.message.Request;
import com.worksap.stm2016.domain.util.CurrentUser;
import com.worksap.stm2016.enums.RequestStatus;
import com.worksap.stm2016.enums.RequestType;
import com.worksap.stm2016.repository.message.RequestRepository;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.ParseException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.worksap.stm2016.specification.BasicSpecs.hasValue;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Shuang on 4/18/2016.
 */
@Controller
public class RequestController {


    @Autowired
    private UserService userService;
    @Autowired
    private RequestRepository requestRepository;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/request", method = RequestMethod.GET, produces = "text/html")
    public String getUserRequestPage(@PathVariable Long id) {
        return "request_employee";
    }

    @ResponseBody
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/request", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getUserRequestList(Authentication authentication,
                                         @PathVariable Long id,
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

    @RequestMapping(value = "/request", method = RequestMethod.GET, produces = "text/html")
    public String getRequestPage() {
        return "request_HR";
    }

    @ResponseBody
    @RequestMapping(value = "/request", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getRequestList(Authentication authentication,
                                         @RequestParam(name = "sort") String sort,
                                         @RequestParam(name = "order") String order,
                                         @RequestParam(name = "limit") Integer limit,
                                         @RequestParam(name = "offset") Integer offset,
                                         @RequestParam(name = "filter", required = false) String filter) throws org.json.simple.parser.ParseException {
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
                } else if (key.equals("title")) { //key = title
                    spec = hasValue(key, search);
                } else { // key = sender
                    spec = hasValue(key, "name", search);
                }
                specs.add(spec);
            }
            if (specs.size() == 1) {
                requests = requestRepository.findAll(specs.get(0), pageable).getContent();
                count = requestRepository.count(specs.get(0));
            } else if (specs.size() == 2) {
                requests = requestRepository.findAll(where(specs.get(0)).and(specs.get(1)), pageable).getContent();
                count = requestRepository.count(where(specs.get(0)).and(specs.get(1)));
            } else if (specs.size() == 3) {
                requests = requestRepository.findAll(where(specs.get(0)).and(specs.get(1)).and(specs.get(2)), pageable).getContent();
                count = requestRepository.count(where(specs.get(0)).and(specs.get(1)).and(specs.get(2)));
            }
        } else {
            requests = requestRepository.findAll(pageable).getContent();
            count = requestRepository.count();
        }

        JSONObject result = new JSONObject();
        result.put("rows", requests);
        result.put("total", count);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
    public Request getRequest(@PathVariable Long id) {
        return requestRepository.findOne(id);
    }

    private final List<SseEmitter> emitters = new ArrayList<>();

    @RequestMapping(path = "/stream", method = RequestMethod.GET)
    public SseEmitter stream() throws IOException {

        SseEmitter emitter = new SseEmitter();

        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));

        return emitter;
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}/request", method = RequestMethod.POST)
    public String sendMessage(Authentication authentication,
                              @PathVariable Long id,
                              @RequestParam(name = "title") String title,
                              @RequestParam(name = "senderMessage", required = false) String senderMessage,
                              @RequestParam(name = "type") String type) throws ParseException {

        User sender = ((CurrentUser) authentication.getPrincipal()).getUser();

        Request request = new Request();
        request.setSender(sender);
        request.setTitle(title);
        request.setSenderMessage(senderMessage);
        request.setType(RequestType.valueOf(type));
        request.setSendDate(new Date());
        Request myRequest;
        if (request.getType() == RequestType.RESIGNATION && requestRepository.findBySenderAndType(sender, RequestType.RESIGNATION).size() > 0) {
            return "Your have already sent a request for resignation.";
        } else {
            myRequest = requestRepository.save(request);
            new ArrayList<SseEmitter>(emitters).forEach((SseEmitter emitter) -> {
                try {
                    emitter.send(myRequest, MediaType.APPLICATION_JSON);
                } catch (IOException e) {
                    emitter.complete();
                    emitters.remove(emitter);
                    e.printStackTrace();
                }
            });
            return "Your request has been sent.";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/request/{id}", method = RequestMethod.POST)
    public String updateRequest(Authentication authentication,
                                @PathVariable Long id,
                                @RequestParam(name = "replierMessage") String replierMessage,
                                @RequestParam(name = "status") String status) throws ParseException {

        User replier = ((CurrentUser) authentication.getPrincipal()).getUser();
        Request request = requestRepository.findOne(id);
        request.setStatus(RequestStatus.valueOf(status));
        request.setReplierMessage(replierMessage);
        request.setReplier(replier);
        request.setReplyDate(new Date());

        Request myRequest = requestRepository.save(request);


        return "Your reply has been sent";
    }
}