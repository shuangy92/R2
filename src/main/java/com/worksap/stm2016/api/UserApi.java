package com.worksap.stm2016.api;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.api.util.JsonResponse.ResponseStatus;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.UserProfile;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.repository.UserProfileRepository;
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

import static com.worksap.stm2016.specification.BasicSpecs.SortAndFilter;
import static com.worksap.stm2016.specification.BasicSpecs.hasValue;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/user")
public class UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserProfileRepository userProfileRepository;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id){
        logger.debug("user.get({})", id);
        return userRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse save(@RequestBody User user){
        if (userRepository.findOneByEmail(user.getEmail()) != null) {
            return new JsonResponse(ResponseStatus.ERROR, "The same email exists");
        } else {
            user = userRepository.save(user);
            return new JsonResponse(ResponseStatus.OK, "User saved with id " + user.getId());
        }
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public JsonResponse register(@RequestBody UserRegisterForm form){
        if (userRepository.findOneByEmail(form.getEmail()) != null) {
            return new JsonResponse(ResponseStatus.ERROR, "The same email exists");
        } else {
            userService.register(form);
            return new JsonResponse(ResponseStatus.OK, "User saved");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse delete(@RequestBody User user){
        userRepository.delete(user);
        if (userProfileRepository.findOne(user.getId()) != null) {
            userProfileRepository.delete(user.getId());
        }
        return new JsonResponse(ResponseStatus.OK, "User " + user.getName() + " deleted");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getUserList(@RequestParam(name = "sort") String sort,
                                  @RequestParam(name = "order") String order,
                                  @RequestParam(name = "limit") Integer limit,
                                  @RequestParam(name = "offset") Integer offset,
                                  @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                if (key.equals("role")) {
                    spec = isValue(key, Role.valueOf(search));
                } else if (key.equals("status")) {
                    spec = isValue(key, UserStatus.valueOf(search));
                } else if (key.equals("id")) {
                    spec = isValue(key, Long.parseLong(search));
                } else { //key = name
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
        }

        JSONObject result = SortAndFilter( sort,  order,  limit,  offset,  filter,  specs, userRepository);
        return result;
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value="/profile/{id}", method = RequestMethod.GET)
    public UserProfile getProfile(@PathVariable("id") Long id){
        logger.debug("user.get({})", id);
        return userProfileRepository.findOne(id);
    }
    @RequestMapping(value="/profile", method = RequestMethod.POST)
    public void saveOrUpdateProfile(@RequestBody UserProfile userProfile){
        userProfileRepository.save(userProfile);
        User user = userRepository.findOne(userProfile.getId());
        user.setName(userProfile.getName());
        userRepository.save(user);
    }
}
