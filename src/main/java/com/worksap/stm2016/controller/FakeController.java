package com.worksap.stm2016.controller;


import com.worksap.stm2016.domain.Department;
import com.worksap.stm2016.domain.job.Job;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.job.JobCategory;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.repository.DepartmentRepository;
import com.worksap.stm2016.repository.job.JobCategoryRepository;
import com.worksap.stm2016.repository.job.JobRepository;
import com.worksap.stm2016.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class FakeController {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    JobCategoryRepository jobCategoryRepository;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public void data() {
        Department department = new Department();
        department.setLocation("shanghai" );
        department.setName("sales");
        departmentRepository.save(department);

        department = departmentRepository.findOne(Long.valueOf(2));
       // department.setManager(admin);
        //departmentRepository.save(department);

        for (Integer i = 1; i < 20; i++) {
            Job job = new Job();
            job.setDescription("Description for job " + i.toString());
            job.setHours(40);
            job.setRequirement("Requirement for job " + i.toString());
            job.setTitle("Job Title No. " + i.toString());
            if (i < 10) {
                job.setDepartment(department);
            } else {
                department = departmentRepository.findOne(Long.valueOf(1));
                job.setDepartment(department);
            }
            Random rand = new Random();
            int  n = rand.nextInt(4) + 1;
            JobCategory jobCategory = jobCategoryRepository.findOne((long) n);
            job.setJobCategory(jobCategory);
            jobRepository.save(job);

            User user = new User();
            user.setName("user name no." + i.toString());
            user.setEmail(i.toString() + "@gmail.com");
            user.setPasswordHash(new BCryptPasswordEncoder().encode("pass"));
            user.setRole(Role.EMPLOYEE);
            user.setStatus(UserStatus.NORMAL);
            user.setDepartment(department);
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