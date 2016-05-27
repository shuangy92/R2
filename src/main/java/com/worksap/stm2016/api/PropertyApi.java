package com.worksap.stm2016.api;


import com.worksap.stm2016.domain.Property;
import com.worksap.stm2016.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/property")
public class PropertyApi {

    private static final Logger logger = LoggerFactory.getLogger(PropertyApi.class);

    @Autowired
    PropertyRepository propertyRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Property> getAll() {
        return propertyRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update (@RequestBody Iterable<Property> properties) {
        for (Property property: properties) {
            propertyRepository.save(property);
        }
    }
}
