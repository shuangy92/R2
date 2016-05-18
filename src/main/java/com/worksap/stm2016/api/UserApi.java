package com.worksap.stm2016.api;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.api.util.JsonResponse.ResponseStatus;
import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.UserProfile;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.repository.UserProfileRepository;
import com.worksap.stm2016.repository.UserRepository;
import com.worksap.stm2016.service.job.DepartmentService;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

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
    UserProfileRepository userProfileRepository;
    @Autowired
    DepartmentService departmentService;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id){
        return userService.get(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                              @RequestParam(name = "order") String order,
                              @RequestParam(name = "limit") Integer limit,
                              @RequestParam(name = "offset") Integer offset,
                              @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return userService.getList( sort, order, limit, offset, filter);
    }

    @RequestMapping(value="/name", method = RequestMethod.GET)
    public Iterable<User> getByNameContaining(@RequestParam(name = "name") String name) {
        return userService.getByNameContaining(name);
    }

    @RequestMapping(value="/department/{id}", method = RequestMethod.GET)
    public Iterable<User> getAllByDepartment(@PathVariable("id") Long id) {
        return userService.getAllByDepartment(departmentService.get(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse save(@RequestBody User user){
        if (userService.save(user) == null) {
            return new JsonResponse(ResponseStatus.ERROR, "The same email exists");
        } else {
            return new JsonResponse(ResponseStatus.OK, "User saved");
        }
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public JsonResponse register(@RequestBody UserRegisterForm form){
        if (userService.register(form) == null) {
            return new JsonResponse(ResponseStatus.ERROR, "The same email exists");
        } else {
            return new JsonResponse(ResponseStatus.OK, "User saved");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse delete(@RequestBody User user){
        userService.delete(user);
        if (userProfileRepository.findOne(user.getId()) != null) {
            userProfileRepository.delete(user.getId());
        }
        return new JsonResponse(ResponseStatus.OK, "User " + user.getName() + " deleted");
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value="/profile/{id}", method = RequestMethod.GET)
    public UserProfile getProfile(@PathVariable("id") Long id){
        UserProfile userProfile = userProfileRepository.findOne(id);
        if (userService.get(id).getName() != null) {
            userProfile.setName(userService.get(id).getName());
        }
        return userProfile;
    }
    @RequestMapping(value="/profile", method = RequestMethod.POST)
    public void saveOrUpdateProfile(@RequestBody UserProfile userProfile){
        userProfileRepository.save(userProfile);
        User user = userService.get(userProfile.getId());
        user.setName(userProfile.getName());
        userService.update(user);
    }

    @RequestMapping(value="/change_password", method = RequestMethod.POST)
    public JsonResponse changePassword(Authentication authentication, @RequestBody JSONObject obj){
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        if (!(new BCryptPasswordEncoder().matches((String)obj.get("oldPassword"), user.getPasswordHash()))) {
            return new JsonResponse(ResponseStatus.ERROR, "The old password is incorrect");
        }
        user.setPasswordHash(new BCryptPasswordEncoder().encode((String)obj.get("password")));
        userService.update(user);
        return new JsonResponse(ResponseStatus.OK, "Password changed");
    }

    @RequestMapping(value="/change_email", method = RequestMethod.POST)
    public JsonResponse changeEmail(Authentication authentication, @RequestBody JSONObject obj){
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        user.setEmail((String)obj.get("email"));
        if (userService.save(user) == null) {
            return new JsonResponse(ResponseStatus.ERROR, "New E-mail address already exists");
        }
        return new JsonResponse(ResponseStatus.OK, "Email changed");
    }
}
