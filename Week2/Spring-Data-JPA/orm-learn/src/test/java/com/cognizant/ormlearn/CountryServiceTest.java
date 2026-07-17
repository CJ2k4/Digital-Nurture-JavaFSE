package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("h2")
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void contextLoadsAndServiceIsInjected() {
        assertNotNull(countryService, "CountryService should be wired by the container");
    }

    @Test
    void getAllCountriesReturnsRowsFromTheCountryTable() {
        List<Country> countries = countryService.getAllCountries();

        assertFalse(countries.isEmpty(), "country table should be populated by data.sql");
        assertEquals(13, countries.size());
    }

    @Test
    void countryIsMappedToTheCorrectColumns() {
        List<Country> countries = countryService.getAllCountries();

        Country india = countries.stream()
                .filter(country -> "IN".equals(country.getCode()))
                .findFirst()
                .orElse(null);

        assertNotNull(india, "IN should be present");
        assertEquals("India", india.getName());
        assertTrue(india.toString().contains("code=IN"));
    }
}
