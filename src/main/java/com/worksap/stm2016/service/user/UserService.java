package com.worksap.stm2016.service.user;

import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.domain.job.Department;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

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

    public JSONObject getList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {

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
                } else if (key.equals("department")) {
                    spec = isValue(key, "name", search);
                } else if (key.equals("departmentId")) {
                    spec = isValue("department", "id", Long.parseLong(search));
                } else if (key.equals("location")) {
                    spec = isValue("department", "location", search);
                } else if (key.equals("id")) {
                    spec = isValue(key, Long.parseLong(search));
                } else if (key.equals("notRole")) {
                    spec = isNotValue("role", Role.valueOf(search));
                } else { //key = name
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
        }

        JSONObject result = andFilter( sort,  order,  limit,  offset,  filter,  specs, userRepository);
        return result;
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
