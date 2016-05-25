package com.worksap.stm2016.controller;

import com.worksap.stm2016.repository.message.RequestRepository;
import com.worksap.stm2016.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shuang on 4/18/2016.
 */
@Controller
public class RequestController {


    @Autowired
    private UserService userService;
    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String getRequestListPage() {
        return "request/request_list";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/request", method = RequestMethod.GET)
    public String getUserRequestListPage(@PathVariable Long id) {
        return "request/user_request_list";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/user/{id}/request/submit", method = RequestMethod.GET)
    public String getRequestSubmitPage(@PathVariable Long id, Model model) {
        return "request/request_form";
    }

    @RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
    public String getRequestPage(@PathVariable Long id, Model model) {
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