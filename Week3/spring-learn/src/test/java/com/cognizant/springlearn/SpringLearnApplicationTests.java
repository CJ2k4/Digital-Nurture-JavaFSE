package com.cognizant.springlearn;

import com.cognizant.springlearn.controller.CountryController;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CountryController countryController;

    @Autowired
    private CountryService countryService;

    @Value("${jwt.secret}")
    private String secret;

    @Test
    void contextLoads() {
        assertNotNull(countryController);
    }

    // Hands-on: Hello World RESTful Web Service
    @Test
    void testSayHello() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!!"));
    }

    // Hands-on: REST - Country Web Service
    @Test
    void testGetCountryIndia() throws Exception {
        ResultActions actions = mvc.perform(get("/country"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    // Hands-on: REST - Get country based on country code
    @Test
    void testGetCountryByCode() throws Exception {
        mvc.perform(get("/countries/IN").with(httpBasic("user", "pwd")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("IN"))
                .andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    void testGetCountryByCodeIsCaseInsensitive() throws Exception {
        mvc.perform(get("/countries/in").with(httpBasic("user", "pwd")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("IN"));

        mvc.perform(get("/countries/jP").with(httpBasic("user", "pwd")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Japan"));
    }

    @Test
    void testGetCountryNotFoundReturns404() throws Exception {
        mvc.perform(get("/countries/az").with(httpBasic("user", "pwd")))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCountryServiceThrowsWhenCodeMissing() {
        assertThrows(CountryNotFoundException.class, () -> countryService.getCountry("zz"));
    }

    // Hands-on: Create authentication service that returns JWT
    @Test
    void testAuthenticateReturnsToken() throws Exception {
        mvc.perform(get("/authenticate").with(httpBasic("user", "pwd")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testTokenSubjectIsTheAuthenticatedUser() throws Exception {
        String body = mvc.perform(get("/authenticate").with(httpBasic("user", "pwd")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = body.replaceAll(".*\"token\"\\s*:\\s*\"([^\"]+)\".*", "$1");

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String subject = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

        assertEquals("user", subject, "JWT subject should be the authenticated username");
    }

    @Test
    void testAuthenticateRequiresCredentials() throws Exception {
        mvc.perform(get("/authenticate"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testCountriesRequiresUserRole() throws Exception {
        mvc.perform(get("/countries/IN").with(httpBasic("admin", "pwd")))
                .andExpect(status().isForbidden());
    }
}
