package com.example.nasaapp.providers;

import com.example.nasaapp.models.responses.Asteroid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Map;

@Service
public class NasaProvider {

    public Asteroid getAsteroidByPlanet() {

        try {

            URIBuilder uriBuilder = new URIBuilder("https://api.nasa.gov/neo/rest/v1/feed");
            uriBuilder.addParameter("start_date", "2020-09-09");
            uriBuilder.addParameter("end_date", "2020-09-16");
            uriBuilder.addParameter("api_key", "hOMBln4aaqylauUuMn2g5cD5heGAqsAasf43LZwr");

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.getForEntity(uriBuilder.build(), String.class);
            if (result != null && HttpStatus.SC_OK == result.getStatusCode().value()) {
                return new ObjectMapper().readValue(result.getBody(), Asteroid.class);
            } else {
                return null;
            }

        } catch (Exception e) {
            // Print a log message
            return null;
        }

    }

}
