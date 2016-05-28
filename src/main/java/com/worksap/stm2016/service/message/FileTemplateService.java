package com.worksap.stm2016.service.message;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.message.Email;
import com.worksap.stm2016.domain.message.FileTemplate;
import com.worksap.stm2016.repository.message.FileTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FileTemplateService {
    @Autowired
    FileTemplateRepository fileTemplateRepository;

    public FileTemplate get(Long id) {
        return fileTemplateRepository.findOne(id);
    }

    public FileTemplate save(FileTemplate fileTemplate) {
        return fileTemplateRepository.save(fileTemplate);
    }

    public FileTemplate update(FileTemplate fileTemplate) {
        return fileTemplateRepository.save(fileTemplate);
    }
}
