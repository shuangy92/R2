package com.worksap.stm2016.service.message;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.Property;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.domain.message.Email;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.domain.user.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.repository.PropertyRepository;
import com.worksap.stm2016.repository.message.NotificationRepository;
import com.worksap.stm2016.repository.user.UserRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
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
    @Autowired
    EmailService emailService;
    @Autowired
    PropertyRepository propertyRepository;

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

        if (propertyRepository.findOne(Property.PropertyName.emailNotification).getValue() == 1) {
            Email email = new Email();
            String x = expiringCount == 1 ? "contract" : "contracts";
            email.setSubject("[NOTIFICATION]: " + expiringCount + " " + x + " will expire in your department from " + df.format(from) + " to " + df.format(to));
            email.setBody("Please login to check the detail.");
            email.setTo(manager);
            try {
                emailService.send(email);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return notificationRepository.save(notification);
    }
    public Notification createContractExpiredNotification(Contract contract) {
        Notification notification = new Notification();

        notification.setType(Notification.NotificationType.CONTRACT_EXPIRED);
        notification.setUser(contract.getJob().getDepartment().getManager());
        JSONObject content = new JSONObject();
        content.put("employee", contract.getUser().getName());
        notification.setContent(content.toString());

        if (propertyRepository.findOne(Property.PropertyName.emailNotification).getValue() == 1) {
            Email email = new Email();
            email.setSubject("[NOTIFICATION]: " + contract.getUser().getName() + "'s contract has expired");
            email.setTo(contract.getJob().getDepartment().getManager());
            try {
                emailService.send(email);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
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

        if (propertyRepository.findOne(Property.PropertyName.emailNotification).getValue() == 1) {
            Email email = new Email();
            email.setSubject("[NOTIFICATION]: You have some job applicants\' profiles to review.");
            email.setBody("[Notes] " + sender.getName() + ": " + notes + "<br/>Please login to check the detail.");
            email.setFrom(sender);
            email.setTo(userRepository.findOne(user.getId()));
            try {
                emailService.send(email);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
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

        if (propertyRepository.findOne(Property.PropertyName.emailNotification).getValue() == 1) {
            Email email = new Email();
            email.setSubject("[NOTIFICATION]: You have an interview at " + new LocalDate(start).toString("yyyy/MM/dd") + " from " + (new LocalTime(start)).toString("HH:mm") + " to " + (new LocalTime(end)).toString("HH:mm"));
            email.setBody("[Notes] " + sender.getName() + ": " + notes + "<br/>Please login to check the detail.");
            email.setFrom(sender);
            email.setTo(userRepository.findOne(user.getId()));
            try {
                emailService.send(email);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return notificationRepository.save(notification);
    }
}
