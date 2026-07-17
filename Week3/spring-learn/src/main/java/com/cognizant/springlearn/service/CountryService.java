package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @SuppressWarnings("unchecked")
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START");
        LOGGER.debug("code={}", code);

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            List<Country> countryList = (List<Country>) context.getBean("countryList", List.class);

            Country country = countryList.stream()
                    .filter(candidate -> candidate.getCode().equalsIgnoreCase(code))
                    .findFirst()
                    .orElseThrow(() -> new CountryNotFoundException("Country not found for code: " + code));

            LOGGER.debug("country={}", country);
            LOGGER.info("END");
            return country;
        }
    }
}
