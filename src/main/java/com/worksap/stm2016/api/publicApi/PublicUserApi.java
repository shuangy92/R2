package com.worksap.stm2016.api.publicApi;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.api.util.JsonResponse.ResponseStatus;
import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.repository.UserRepository;
import com.worksap.stm2016.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/public/user")
public class PublicUserApi {

    private static final Logger logger = LoggerFactory.getLogger(PublicUserApi.class);
    @Autowired
    UserService userService;

    @RequestMapping(value="/current", method = RequestMethod.GET)
    public User getCurrentUser(Authentication authentication){
        if (authentication == null) {
            return null;
        } else {
            return ((CurrentUser) authentication.getPrincipal()).getUser();
        }
    }
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id){
        return userService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse register(@RequestBody UserRegisterForm form){
        if (userService.register(form) == null) {
            return new JsonResponse(ResponseStatus.ERROR, "The same email exists");
        } else {
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
