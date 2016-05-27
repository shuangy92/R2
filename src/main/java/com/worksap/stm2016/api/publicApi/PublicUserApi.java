package com.worksap.stm2016.api.publicApi;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.api.util.JsonResponse.ResponseStatus;
import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.service.user.UserProfileService;
import com.worksap.stm2016.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.worksap.stm2016.api.util.JsonResponse.deletionResponse;

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
    UserProfileService userProfileService;

    @RequestMapping(value="/current", method = RequestMethod.GET)
    public User getCurrentUser(Authentication authentication){
        if (authentication == null) {
            return null;
        } else {
            return ((CurrentUser) authentication.getPrincipal()).getUser();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse register(@RequestBody User user) {
        if (userService.create(user) == null) {
            return new JsonResponse(ResponseStatus.ERROR, "The same email exists");
        } else {
            return new JsonResponse(ResponseStatus.OK, "User saved");
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResponse delete(@PathVariable Long id){
        Long result = userProfileService.delete(id);
        return deletionResponse(id, result);
    }
}
