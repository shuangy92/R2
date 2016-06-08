package com.worksap.stm2016.service.user;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.job.Department;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.domain.user.UserProfile;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.user.UserProfileRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.worksap.stm2016.specification.BasicSpecs.*;
import static org.springframework.data.jpa.domain.Specifications.where;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    public User get(long id) {
        return userRepository.findOne(id);
    }

    public User getByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public Iterable<User> getAllByDepartment(Department department) {
        return userRepository.findByDepartment(department);
    }
    /*public List<User> getAllByDepartmentAndLocation(Department department, String location) {
        Specifications spec;
        if (department != null && location != null) {
            spec = where(isValue("user", "department", department)).and(isValue("user", "department", "location", location));
        } else if (department != null) {
            spec = where(isValue("user", "department", department));
        } else if (location != null) {
            spec = where(isValue("user", "department", "location", location));
        } else {
            return (List<User>) userRepository.findAll();
        }
        return userRepository.findAll(spec);

    }*/

    public JsonArrayResponse getList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = key.equals("roleList") ? "" : (String) filterObj.get(key);
                Specification spec = null;
                switch (key) {
                    case "roleList":
                        List<Role> roleList = new ArrayList<>();
                        for (String status : (List<String>) filterObj.get(key)) {
                            roleList.add(Role.valueOf(status));
                        }
                        if (roleList.size() == 0) {
                            return new JsonArrayResponse(new ArrayList<>(), 0);
                        }
                        spec = inValue("role", roleList);
                        break;
                    case "role":
                        spec = isValue(key, Role.valueOf(search));
                        break;
                    case "name":
                        spec = hasValue(key, search);
                        break;
                    case "department":
                        spec = isValue(key, "name", search);
                        break;
                    case "departmentId":
                        spec = isValue("department", "id", Long.parseLong(search));
                        break;
                    case "location":
                        spec = isValue("department", "location", search);
                        break;
                    case "id":
                        spec = isValue(key, Long.parseLong(search));
                        break;
                }
                specs.add(spec);
            }
        }

        return andFilter( sort,  order,  limit,  offset,  filter,  specs, userRepository);
    }

    public Iterable<User> getByNameContaining(String name) {
        return userRepository.findByNameContainingIgnoreCaseAndRole(name, Role.EMPLOYEE);
    }

    public User save(User user){
        if (userRepository.findOneByEmail(user.getEmail()) != null) {
            return null;
        } else {
            return userRepository.save(user);
        }
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.findOneByEmail(user.getEmail()) != null) {
            return null;
        } else {
            user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPassword()));
            user = userRepository.save(user);

            UserProfile userProfile = new UserProfile();
            userProfile.setUser(user);
            userProfileRepository.save(userProfile);

            return user;
        }
    }
}
