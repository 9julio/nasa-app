package com.jafernandez.nasaapp.unit;

import com.jafernandez.nasaapp.models.responses.Asteroid;
import com.jafernandez.nasaapp.models.responses.AsteroidResponse;
import com.jafernandez.nasaapp.providers.NasaProvider;
import com.jafernandez.nasaapp.services.NasaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NasaServiceUnitTests {

    @InjectMocks
    public NasaService nasaService;

    @Mock
    public NasaProvider nasaProvider;

    @Test
    public void test_getAsteroids_OK() {

        Map<String, Object> map = new HashMap<>();
        map.put("is_potentially_hazardous_asteroid", true);

        List<Map<String, Object>> listMapStringObject = new ArrayList<>();

        Map<String, Object> mapStringObject = new HashMap<>();
        mapStringObject.put("orbiting_body", "earth");
        mapStringObject.put("close_approach_date", "someDate");
        mapStringObject.put("relative_velocity", new HashMap<String, String>() {{ put("kilometers_per_hour", "1"); }});

        listMapStringObject.add(mapStringObject);

        Map<String, Map<String, Double>> diameters = new HashMap<>();

        Map<String, Double> diametersMinMax = new HashMap<>();
        diametersMinMax.put("estimated_diameter_max", 1.0);
        diametersMinMax.put("estimated_diameter_min", 3.0);

        diameters.put("kilometers", diametersMinMax);

        map.put("estimated_diameter", diameters);
        map.put("close_approach_data", listMapStringObject);
        map.put("name", "theNameOfTheAsteroid");

        Map<String, Object> mapAsteroid = new HashMap<>();
        mapAsteroid.put("2021-09-15", Arrays.asList(map));

        Asteroid asteroid = new Asteroid(null, 1, mapAsteroid);

        when(this.nasaProvider.getAsteroids(
                anyString(),
                anyString()
        )).thenReturn(asteroid);

        List<AsteroidResponse> result = this.nasaService.getAsteroids("earth");
        assertNotNull(result);
    }

    @Test
    public void test_getAsteroids_OKWithEmptyResponse() {

        when(this.nasaProvider.getAsteroids(
                anyString(),
                anyString()
        )).thenReturn(null);

        List<AsteroidResponse> result = this.nasaService.getAsteroids("earth");
        assertNull(result);
    }

}
