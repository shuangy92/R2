package com.worksap.stm2016.service.message;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.message.FileTemplate;
import com.worksap.stm2016.repository.message.FileTemplateRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.andFilter;
import static com.worksap.stm2016.specification.BasicSpecs.hasValue;

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

    public Iterable<FileTemplate> getAll() {
        return fileTemplateRepository.findAll();
    }

    public JsonArrayResponse getList(String sort, String order, Integer limit, Integer offset, String filter) throws ParseException {
        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec = null;
                switch (key) {
                    case "author":
                        spec = hasValue(key, "name", search);
                        break;
                    case "title":
                        spec = hasValue(key, search);
                        break;
                }
                specs.add(spec);
            }
        }
        return andFilter(sort, order, limit, offset, filter, specs, fileTemplateRepository);
    }

    public FileTemplate save(FileTemplate fileTemplate) {
        try{
            return fileTemplateRepository.save(fileTemplate);
        } catch(Exception e){
            return null;
        }
    }

    public Long deleteList(ArrayList<Long> ids){
        for (Long id: ids) {
            try {
                fileTemplateRepository.delete(id);
            } catch (Exception e) {
                return id;
            }
        }
        return Long.valueOf(0);
    }
}
