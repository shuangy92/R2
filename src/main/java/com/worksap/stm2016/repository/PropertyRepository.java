package com.worksap.stm2016.repository;


import com.worksap.stm2016.domain.Country;
import com.worksap.stm2016.domain.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Property.PropertyName> {
}
