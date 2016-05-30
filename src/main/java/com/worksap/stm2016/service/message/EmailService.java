package com.worksap.stm2016.service.message;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.FileProfile;
import com.worksap.stm2016.domain.message.Email;
import com.worksap.stm2016.repository.FileProfileRepository;
import com.worksap.stm2016.service.FileService;
import com.worksap.stm2016.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
    @Autowired
    private FileProfileRepository fileProfileRepository;

    public JsonResponse send(Email email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setFrom(new InternetAddress(email.getFrom().getEmail(), email.getFrom().getName()));
        helper.setTo(new InternetAddress(email.getTo().getEmail(), email.getTo().getName()));
        // helper.setReplyTo(replyTo);
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody(), true);

        if (email.getAttachments() != null) {
            for (Long id : email.getAttachments()) {
                FileSystemResource file = new FileSystemResource(fileProfileRepository.findOne(id).getPath());
                helper.addAttachment(file.getFilename(), file);
            }
        }

        javaMailSender.send(mail);
        return new JsonResponse(JsonResponse.ResponseStatus.OK, "Email sent");
    }
}
