package com.worksap.stm2016.api.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.message.FileTemplate;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import com.worksap.stm2016.service.message.FileTemplateService;
import com.worksap.stm2016.service.recruitment.JobApplicationService;
import com.worksap.stm2016.util.FileUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.worksap.stm2016.api.util.JsonResponse.deletionResponse;

/**
 * Created by Shuang on 4/27/2016.
 */
@RestController
@RequestMapping("/api/file_template")
public class FileTemplateApi {
    @Autowired
    private FileTemplateService fileTemplateService;
    @Autowired
    private JobApplicationService jobApplicationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONObject get(@PathVariable("id") Long id) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        FileTemplate ft = fileTemplateService.get(id);
        JSONObject fileTemplate = (JSONObject) (new JSONParser()).parse(mapper.writeValueAsString(ft));
        fileTemplate.put("content", ft.getContent());
        return fileTemplate;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<FileTemplate> getAll() throws JsonProcessingException, ParseException {
        return fileTemplateService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                              @RequestParam(name = "order") String order,
                              @RequestParam(name = "limit") Integer limit,
                              @RequestParam(name = "offset") Integer offset,
                              @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return fileTemplateService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public FileTemplate save(@RequestBody FileTemplate fileTemplate) {
        return fileTemplateService.save(fileTemplate);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public FileTemplate update(@RequestBody FileTemplate fileTemplate) {
        return fileTemplateService.update(fileTemplate);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse deleteList(@RequestBody ArrayList<Long> ids){
        Long result = fileTemplateService.deleteList(ids);
        return deletionResponse(null, result);
    }
    @RequestMapping(value = "preview", method = RequestMethod.POST)
    public String preview(@RequestBody JSONObject obj, Authentication authentication) {
        JobApplication jobApplication = jobApplicationService.get(Long.valueOf((Integer) obj.get("id")));
        String html = (String) obj.get("html");
        return FileUtil.parseHtmlWithJobApplication(html, jobApplication, authentication);
    }
}
