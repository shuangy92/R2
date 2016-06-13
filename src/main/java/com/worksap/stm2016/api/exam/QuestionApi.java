package com.worksap.stm2016.api.exam;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.exam.Question;
import com.worksap.stm2016.service.exam.QuestionService;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/exam")
public class QuestionApi {

    private static final Logger logger = LoggerFactory.getLogger(QuestionApi.class);
    @Autowired
    QuestionService questionService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Question get(@PathVariable("id") Long id){
        return questionService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonArrayResponse getList(@RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return questionService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Question save(@RequestBody Question question){
        return questionService.save(question);
    }
}
