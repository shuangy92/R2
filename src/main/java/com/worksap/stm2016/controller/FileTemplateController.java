package com.worksap.stm2016.controller;

import com.worksap.stm2016.domain.message.FileTemplate;
import com.worksap.stm2016.repository.FileProfileRepository;
import com.worksap.stm2016.service.message.FileTemplateService;
import com.worksap.stm2016.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.springframework.util.StringUtils.getFilenameExtension;

@Controller
public class FileTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(FileTemplateController.class);
    @Autowired
    FileTemplateService fileTemplateService;


    @RequestMapping(value = "file_template", method = RequestMethod.GET)
    public String getFileTemplateListPage() {
        return "file_template_list";
    }
}