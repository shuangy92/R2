package com.worksap.stm2016.service.message;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.message.NotificationRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.worksap.stm2016.specification.BasicSpecs.isValue;
import static com.worksap.stm2016.specification.BasicSpecs.orFilter;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    public Notification get(Long id) {
        return notificationRepository.findOne(id);
    }


    public JsonArrayResponse getList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {

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

        return orFilter(sort, order, limit, offset, filter, specs, notificationRepository);
    }

    public Notification update (Notification notification) {
        return notificationRepository.save(notification);
    }

    /*public Notification createReviewNotification (JobApplication jobApplication, ReviewResponse reviewResponse, Notification.NotificationType type) {
        Notification notification = new Notification();
        switch (type) {
            case REVIEW_START:
                notification.setContent("You have a new review task: " + reviewResponse.getReviewRun().getTask());
                notification.setType(Notification.NotificationType.REVIEW_START);
                notification.setItemId(jobApplication.getId());
                notification.setUser(reviewResponse.getReviewer());
                break;
            case REVIEW_UPDATE:
                notification.setContent("It's your turn for review: " + reviewResponse.getReviewRun().getTask());
                notification.setType(type);
                notification.setItemId(jobApplication.getId());
                notification.setUser(reviewResponse.getReviewer());
                break;
            case REVIEW_UPDATE_HR:
                notification.setContent("Review \"" + reviewResponse.getReviewRun().getTask() + "\" for application " + jobApplication.getId() + " has finished.");
                notification.setType(type);
                notification.setItemId(jobApplication.getId());
                notification.setRole(Role.ADMIN);
                break;

        }
        return notificationRepository.save(notification);
    }*/
    public Notification createContractExpiringNotification(User manager, Long expiringCount, Date from, Date to) {
        Notification notification = new Notification();
        notification.setUser(manager);
        notification.setType(Notification.NotificationType.CONTRACT_EXPIRING);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        JSONObject content = new JSONObject();
        content.put("count", expiringCount);
        content.put("from", df.format(from));
        content.put("to", df.format(to));
        notification.setContent(content.toString());
        return notificationRepository.save(notification);
    }
    public Notification createContractNotification(Contract contract, Notification.NotificationType type) {
        Notification notification = new Notification();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        switch (type) {
            case CONTRACT_EXPIRING: //TODO
                break;
            case CONTRACT_EXPIRED:
                notification.setType(type);
                notification.setUser(contract.getJob().getDepartment().getManager());
                JSONObject content = new JSONObject();
                content.put("employee", contract.getUser().getName());
                notification.setContent(content.toString());
                break;
        }
        return notificationRepository.save(notification);
    }
    public Notification createProfileReviewNotification(User user, User sender, List<Long> applicationIds, List<Long> responseIds, String notes) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setType(Notification.NotificationType.PROFILE_REVIEW);
        JSONObject content = new JSONObject();
        content.put("applications", applicationIds);
        content.put("responses", responseIds);
        content.put("notes", notes);
        content.put("senderName", sender.getName());
        content.put("senderId", sender.getId());
        notification.setContent(content.toString());
        return notificationRepository.save(notification);
    }
    public Notification createInterviewNotification(Long applicationId, Long responseId, User user, User sender, String notes, Date start, Date end) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setType(Notification.NotificationType.INTERVIEW);
        JSONObject content = new JSONObject();
        content.put("applicationId", applicationId);
        content.put("responseId", responseId);
        content.put("notes", notes);
        content.put("senderName", sender.getName());
        content.put("senderId", sender.getId());
        content.put("start", start.toString());
        content.put("end", end.toString());
        notification.setContent(content.toString());
        return notificationRepository.save(notification);
    }
}
