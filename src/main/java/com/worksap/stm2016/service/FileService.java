package com.worksap.stm2016.service;

import com.worksap.stm2016.domain.FileProfile;
import com.worksap.stm2016.repository.FileProfileRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.andFilter;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/file")
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    FileProfileRepository fileProfileRepository;

    public FileProfile getByPath(String path) {
        return fileProfileRepository.findOneByPath(path);
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
                //if (key.equals("user")) {
                spec = isValue("user", "id", Long.parseLong(search));
                //}
                specs.add(spec);
            }
        }

        JSONObject result = andFilter(sort, order, limit, offset, filter, specs, fileProfileRepository);
        return result;
    }

    public FileProfile save(FileProfile fileProfile) {
        return fileProfileRepository.save(fileProfile);
    }

    public boolean delete(Long id) {
        String path = fileProfileRepository.findOne(id).getPath();
        File file = new File(path);
        if (file.delete()) {
            fileProfileRepository.delete(id);
            return true;
        } else {
            return false;
        }
    }
}
