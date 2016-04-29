package com.worksap.stm2016.controller;


import com.worksap.stm2016.domain.Job;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.repository.JobRepository;
import com.worksap.stm2016.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeController {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public void data() {
        for (Integer i = 1; i < 101; i++) {
            Job job = new Job();
            job.setDescription("Description for job " + i.toString());
            job.setHours(40);
            job.setRequirement("Requirement for job " + i.toString());
            job.setTitle("Job Title No. " + i.toString());
            jobRepository.save(job);

            User user = new User();
            user.setName("user name no." + i.toString());
            user.setEmail(i.toString() + "@gmail.com");
            user.setPasswordHash(new BCryptPasswordEncoder().encode("pass"));
            user.setRole(Role.EMPLOYEE);
            user.setStatus(UserStatus.NORMAL);
            userRepository.save(user);
        }
    }

    /*@RequestMapping(value = "/fake", method = RequestMethod.GET)
    public JSONArray fake() {
        JSONArray result = new JSONArray();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(
                    "/Users/Shuang/Downloads/users.json"));

            JSONArray array = (JSONArray) obj;
            for (Object aaa:array) {
                JSONObject jsonObject = (JSONObject) aaa;
                //User user = new User();
                //jsonObject.put ("email", (String) jsonObject.get("email"));
                //jsonObject.put ("name", (String) jsonObject.get("name"));
                jsonObject.put ("password_hash", new BCryptPasswordEncoder().encode((String) jsonObject.get("email")));

                Random random = new Random();
                Role role = Role.values()[random.nextInt(Role.values().length-1)+1];
                jsonObject.put ("role", role);

                UserStatus status = UserStatus.values()[random.nextInt(UserStatus.values().length)];
                jsonObject.put ("status", status);
                result.add(jsonObject);

            }
            try (FileWriter file = new FileWriter("/Users/Shuang/Downloads/file1.txt")) {
                file.write(result.toJSONString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/

}