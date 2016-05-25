package com.worksap.stm2016.service.message;

import com.sun.jmx.remote.security.NotificationAccessController;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.message.NotificationRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    @Autowired
    NotificationRepository notificationRepository;

    public Notification get(Long id) {
        return notificationRepository.findOne(id);
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
                if (key.equals("uid")) {
                    spec = isValue("user", "id", Long.parseLong(search));
                } else { // key = role
                    spec = isValue(key, Role.valueOf(search));
                }
                specs.add(spec);
            }

        }

        JSONObject result = filterOr(sort, order, limit, offset, filter, specs, notificationRepository);
        return result;
    }

    public Notification update (@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }
}
