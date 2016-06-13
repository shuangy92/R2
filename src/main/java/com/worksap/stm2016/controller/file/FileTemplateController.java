package com.worksap.stm2016.controller.file;

import com.worksap.stm2016.service.message.FileTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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