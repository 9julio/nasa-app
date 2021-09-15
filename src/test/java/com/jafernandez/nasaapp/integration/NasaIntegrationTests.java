package com.jafernandez.nasaapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jafernandez.nasaapp.models.responses.AsteroidResponse;
import com.jafernandez.nasaapp.models.responses.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NasaIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_getAsteroids_OK() throws Exception {

        String planet = "earth";

        MvcResult result = mvc.perform(get(String.format("/asteroids?planet=%s", planet)))
                .andExpect(status().isOk())
                .andReturn();

        List<AsteroidResponse> response = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertNotNull(response);
    }

    @Test
    public void test_getAsteroids_OKWithEmptyBody() throws Exception {

        String planet = "myFavouritePlanet";

        MvcResult result = mvc.perform(get(String.format("/asteroids?planet=%s", planet)))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(StringUtils.isEmpty(result.getResponse().getContentAsString()));
    }

    @Test
    public void test_getAsteroids_BadRequest() throws Exception {

        MvcResult result = mvc.perform(get("/asteroids"))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse response = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getCode());
        assertEquals("The planet param is mandatory.", response.getMessage());
    }

}
