package com.worksap.stm2016.api;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.api.util.JsonResponse.ResponseStatus;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.repository.UserRepository;
import com.worksap.stm2016.service.user.UserService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.worksap.stm2016.specification.BasicSpecs.hasValue;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/user")
@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
public class UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id){
        logger.debug("user.get({})", id);
        return userRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse save(@RequestBody User user){
        logger.debug("duty.user({})", user);
        user = userRepository.save(user);
        return new JsonResponse(ResponseStatus.OK, "User saved with id " + user.getId());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse delete(@RequestBody User user){
        logger.debug("user.delete({})", user);
        userRepository.delete(user);
        return new JsonResponse(ResponseStatus.OK, "User " + user.getName() + " deleted");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getUserList(@RequestParam(name = "sort") String sort,
                                   @RequestParam(name = "order") String order,
                                   @RequestParam(name = "limit") Integer limit,
                                   @RequestParam(name = "offset") Integer offset,
                                   @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        Integer page = offset / limit;
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, limit, direction, sort);

        ArrayList<Specification> specs = new ArrayList<>();
        List<User> users;
        Long count = Long.valueOf(0);

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
            if (specs.size() == 1) {
                users = userRepository.findAll(specs.get(0), pageable).getContent();
                count = userRepository.count(specs.get(0));
            } else if (specs.size() == 2) {
                users = userRepository.findAll(where(specs.get(0)).and(specs.get(1)), pageable).getContent();
                count = userRepository.count(where(specs.get(0)).and(specs.get(1)));
            } else if (specs.size() == 3) {
                users = userRepository.findAll(where(specs.get(0)).and(specs.get(1)).and(specs.get(2)), pageable).getContent();
                count = userRepository.count(where(specs.get(0)).and(specs.get(1)).and(specs.get(2)));
            } else { // size = 4
                users = userRepository.findAll(where(specs.get(0)).and(specs.get(1)).and(specs.get(2)).and(specs.get(3)), pageable).getContent();
                count = userRepository.count(where(specs.get(0)).and(specs.get(1)).and(specs.get(2)).and(specs.get(3)));
            }
        } else {
            users = userRepository.findAll(pageable).getContent();
            count = userRepository.count();
        }

        JSONObject result = new JSONObject();
        result.put("rows", users);
        result.put("total", count);

        return result;
    }
}
