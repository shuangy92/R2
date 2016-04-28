package com.worksap.stm2016.api;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.domain.message.Email;
import com.worksap.stm2016.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Shuang on 4/27/2016.
 */
@RestController
@RequestMapping("/api/email")
public class EmailApi {
    @Autowired
    private EmailService emailService;

    @RequestMapping
    public void send(Email email) throws UnsupportedEncodingException, MessagingException {
        emailService.send(email);
    }
}