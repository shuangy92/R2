package com.worksap.stm2016.api.job;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.service.job.ContractService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/contract")
public class ContractApi {

    private static final Logger logger = LoggerFactory.getLogger(ContractApi.class);
    @Autowired
    ContractService contractService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Contract get(@PathVariable("id") Long id){
        return contractService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonArrayResponse getList(@RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return contractService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public JsonArrayResponse getJobHistoryList(@RequestParam(name = "sort") String sort,
                              @RequestParam(name = "order") String order,
                              @RequestParam(name = "limit") Integer limit,
                              @RequestParam(name = "offset") Integer offset,
                              @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return contractService.getJobHistoryList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Contract save(@RequestBody Contract contract){
        return contractService.save(contract);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Contract update(@RequestBody Contract contract){
        return contractService.update(contract);
    }
}
