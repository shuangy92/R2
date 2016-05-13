package com.worksap.stm2016.api.publicApi;

import com.worksap.stm2016.domain.Country;
import com.worksap.stm2016.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shuang on 4/27/2016.
 */
@RestController
@RequestMapping("/api/public/country")
public class PublicCountryApi {
    @Autowired
    private CountryRepository countryRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Country get(@PathVariable("id") Long id){
        return countryRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Country> getCountryList() {
        return countryRepository.findAll();
    }
}
