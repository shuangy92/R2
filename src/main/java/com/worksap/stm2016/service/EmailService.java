package com.worksap.stm2016.service;

import com.sun.org.apache.regexp.internal.RE;
import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.message.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * Created by Shuang on 4/27/2016.
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public JsonResponse send(Email email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setFrom(new InternetAddress("yangshuangstms@gmail.com", email.getFromName()));
        helper.setTo(new InternetAddress(email.getTo(), email.getToName()));
       // helper.setReplyTo(replyTo);
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody(), true);

        javaMailSender.send(mail);
        return new JsonResponse(JsonResponse.ResponseStatus.OK, "Email sent");
    }
}
