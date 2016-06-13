package com.worksap.stm2016;

import com.worksap.stm2016.domain.Property;
import com.worksap.stm2016.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Shuang on 5/25/2016.
 */
@Component
public class Setup {
    @Autowired
    PropertyRepository propertyRepository;

    @PostConstruct
    public void setup() {
        Property property = new Property();
        property.setName(Property.PropertyName.contractNotifyDaysBefore);
        property.setValue(30);
        propertyRepository.save(property);

        property.setName(Property.PropertyName.contractNotifyDayLength);
        property.setValue(30);
        propertyRepository.save(property);

        property.setName(Property.PropertyName.emailNotification);
        property.setValue(0);
        propertyRepository.save(property);
    }

}
