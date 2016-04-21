/*package com.worksap.stm2016.controller;

import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.model.User;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Random;

@RestController
public class FakeController {

    @RequestMapping(value = "/fake", method = RequestMethod.GET)
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
    }

}*/