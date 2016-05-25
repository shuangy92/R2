package com.worksap.stm2016.service.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worksap.stm2016.domain.message.OtherRequest;
import com.worksap.stm2016.domain.message.Request;
import com.worksap.stm2016.domain.message.StaffingRequest;
import com.worksap.stm2016.enums.RequestStatus;
import com.worksap.stm2016.enums.RequestType;
import com.worksap.stm2016.repository.message.RequestRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

@Service
public class RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);
    @Autowired
    RequestRepository requestRepository;

    public Request get(Long id){
        return (Request) requestRepository.findOne(id);
    }

    public JSONObject getList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                if (key.equals("requestType")) {
                    spec = isValue(key, RequestType.valueOf(search));
                } else if (key.equals("status")) {
                    spec = isValue(key, RequestStatus.valueOf(search));
                } else if (key.equals("sendDate")) {
                    spec = isValue(key, new Date(Long.parseLong(search)));
                } else if (key.equals("sender")) {
                    spec = hasValue(key, "name", search);
                } else if (key.equals("uid")) {
                    spec = isValue("sender", "id", Long.parseLong(search));
                } else { // key = title
                    spec = hasValue(key, search);
                }
                specs.add(spec);
            }
        }

        JSONObject result = filterAnd(sort, order, limit, offset, filter, specs, requestRepository);
        return result;
    }

    public Request save(JSONObject requestJson) throws IOException {
        Request request;
        ObjectMapper mapper = new ObjectMapper();
        switch (requestJson.get("requestType").toString()) {
            case ("STAFFING"):
                request = mapper.readValue(requestJson.toString(), StaffingRequest.class);
                break;
            default:
                request = mapper.readValue(requestJson.toString(), OtherRequest.class);
        }
        return (Request) requestRepository.save(request);
    }

    public Request update(JSONObject requestJson) throws IOException {
        Request request;
        ObjectMapper mapper = new ObjectMapper();
        switch (requestJson.get("requestType").toString()) {
            case ("STAFFING"):
                request = mapper.readValue(requestJson.toString(), StaffingRequest.class);
                break;
            default:
                request = mapper.readValue(requestJson.toString(), OtherRequest.class);
        }

        return (Request) requestRepository.save(request);
    }
}
