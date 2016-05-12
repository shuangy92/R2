package com.worksap.stm2016.api.publicApi;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.api.util.JsonResponse.ResponseStatus;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.repository.UserRepository;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/public/user")
public class PublicUserApi {

    private static final Logger logger = LoggerFactory.getLogger(PublicUserApi.class);
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id){
        logger.debug("user.get({})", id);
        return userRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse register(@RequestBody UserRegisterForm form){
        if (userRepository.findOneByEmail(form.getEmail()) != null) {
            return new JsonResponse(ResponseStatus.ERROR, "The same email exists");
        } else {
            userService.register(form);
            return new JsonResponse(ResponseStatus.OK, "User saved");
        }
    }

    /*@RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse delete(@RequestBody User user){
        logger.debug("user.delete({})", user);
        userRepository.delete(user);
        return new JsonResponse(ResponseStatus.OK, "User " + user.getName() + " deleted");
    }*/
}
