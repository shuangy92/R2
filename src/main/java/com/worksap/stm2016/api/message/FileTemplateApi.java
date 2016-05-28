package com.worksap.stm2016.api.message;

import com.worksap.stm2016.domain.message.Email;
import com.worksap.stm2016.domain.message.FileTemplate;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.service.message.EmailService;
import com.worksap.stm2016.service.message.FileTemplateService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Shuang on 4/27/2016.
 */
@RestController
@RequestMapping("/api/file_template")
public class FileTemplateApi {
    @Autowired
    private FileTemplateService fileTemplateService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FileTemplate get(@PathVariable("id") Long id) {
        return fileTemplateService.get(id);
    }

    /*@RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                              @RequestParam(name = "order") String order,
                              @RequestParam(name = "limit") Integer limit,
                              @RequestParam(name = "offset") Integer offset,
                              @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return fileTemplateService.getList(sort, order, limit, offset, filter);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public FileTemplate save(@RequestBody FileTemplate fileTemplate) {
        return fileTemplateService.save(fileTemplate);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public FileTemplate update(@RequestBody FileTemplate fileTemplate) {
        return fileTemplateService.update(fileTemplate);
    }
}
