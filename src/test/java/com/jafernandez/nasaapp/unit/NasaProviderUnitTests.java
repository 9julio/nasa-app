package com.jafernandez.nasaapp.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jafernandez.nasaapp.models.responses.Asteroid;
import com.jafernandez.nasaapp.providers.NasaProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertNotNull;

//@RunWith(MockitoJUnitRunner.class)
public class NasaProviderUnitTests {

//    @InjectMocks
//    public NasaProvider nasaProvider;
//
//    @Test
//    public void test_getAsteroids_OK() throws JsonProcessingException {
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("is_potentially_hazardous_asteroid", true);
//
//        List<Map<String, Object>> listMapStringObject = new ArrayList<>();
//
//        Map<String, Object> mapStringObject = new HashMap<>();
//        mapStringObject.put("orbiting_body", "earth");
//        mapStringObject.put("close_approach_date", "someDate");
//        mapStringObject.put("relative_velocity", new HashMap<String, String>() {{ put("kilometers_per_hour", "1"); }});
//
//        listMapStringObject.add(mapStringObject);
//
//        Map<String, Map<String, Double>> diameters = new HashMap<>();
//
//        Map<String, Double> diametersMinMax = new HashMap<>();
//        diametersMinMax.put("estimated_diameter_max", 1.0);
//        diametersMinMax.put("estimated_diameter_min", 3.0);
//
//        diameters.put("kilometers", diametersMinMax);
//
//        map.put("estimated_diameter", diameters);
//        map.put("close_approach_data", listMapStringObject);
//        map.put("name", "theNameOfTheAsteroid");
//
//        Map<String, Object> mapAsteroid = new HashMap<>();
//        mapAsteroid.put("2021-09-15", Arrays.asList(map));
//
//        Asteroid asteroid = new Asteroid(null, 1, mapAsteroid);
//
//        stubFor(get(urlEqualTo("https://api.nasa.gov/neo/rest/v1/feed"))
//                .willReturn(ok()
//                        .withBody(new ObjectMapper().writeValueAsString(asteroid))));
//
//        Asteroid result = this.nasaProvider.getAsteroids("2021-09-15", "2021-10-05");
//        assertNotNull(result);
//    }

}
