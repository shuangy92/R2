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
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/request", method = RequestMethod.GET, produces = "text/html")
    public String getRequestListPage() {
        return "request/requests_HR";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/request", method = RequestMethod.GET, produces = "text/html")
    public String getUserRequestListPage(@PathVariable Long id) {
        return "request/requests_employee";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/request/submit", method = RequestMethod.GET, produces = "text/html")
    public String getRequestSubmitPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id).get());
        return "request/request_form";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessRequest(principal, #id)")
    @RequestMapping(value = "/request/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getRequestPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id).get());
        return "request/request";
    }

    private final List<SseEmitter> emitters = new ArrayList<>();

    @RequestMapping(path = "/stream", method = RequestMethod.GET)
    public SseEmitter stream() throws IOException {

        SseEmitter emitter = new SseEmitter();

        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));

        return emitter;
    }

    /*@ResponseBody
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
    }*/


}