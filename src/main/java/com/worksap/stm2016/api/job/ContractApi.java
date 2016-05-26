package com.worksap.stm2016.api.job;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.domain.job.Contract;
import com.worksap.stm2016.service.job.ContractService;
import com.worksap.stm2016.service.user.UserProfileService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.worksap.stm2016.api.util.JsonResponse.deletionResponse;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/contract")
public class ContractApi {

    private static final Logger logger = LoggerFactory.getLogger(ContractApi.class);
    @Autowired
    ContractService contractService;
    @Autowired
    UserProfileService userProfileService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Contract get(@PathVariable("id") Long id){
        return contractService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                              @RequestParam(name = "order") String order,
                              @RequestParam(name = "limit") Integer limit,
                              @RequestParam(name = "offset") Integer offset,
                              @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return contractService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Contract save(@RequestBody Contract contract){
        return contractService.save(contract);
    }
}
