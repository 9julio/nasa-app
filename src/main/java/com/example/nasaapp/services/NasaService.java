package com.example.nasaapp.services;

import com.example.nasaapp.models.responses.Asteroid;
import com.example.nasaapp.models.responses.AsteroidResponse;
import com.example.nasaapp.providers.NasaProvider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class NasaService {

    @Autowired
    private NasaProvider nasaProvider;

    public List<AsteroidResponse> getAsteroids(String param) {

        List<AsteroidResponse> asteroidList = new ArrayList<AsteroidResponse>();
        Asteroid asteroid = nasaProvider.getAsteroidByPlanet();

        if (asteroid == null) {
            return null;
        } else {

            for (Map.Entry<String, Object> obj : asteroid.getNearEarthObjects().entrySet()) {

                List<Map<String, Object>> list = (List<Map<String, Object>>) obj.getValue();
                for (Map<String, Object> item : list) {

                    boolean is_potentially_hazardous_asteroid = BooleanUtils.toBoolean(item.get("is_potentially_hazardous_asteroid").toString());
                    String name = null;
                    double diameterMedium = 0.0;
                    String velocity = null;
                    String date = null;
                    String planet = null;

                    if (is_potentially_hazardous_asteroid) {

                        List<Map<String, Object>> otherDatas = (List<Map<String, Object>>) item.get("close_approach_data");
                        for (Map<String, Object> data : otherDatas) {
                            planet = String.valueOf(data.get("orbiting_body"));
                            velocity = ((Map<String, String>) data.get("relative_velocity")).get("kilometers_per_hour");
                            date = String.valueOf(data.get("close_approach_date"));
                        }

                        if (param.equalsIgnoreCase(planet)) {

                            // Name of asteroid
                            name = item.get("name").toString();

                            // Calculate the diameter
                            Map<String, Map<String, Double>> metrics = (Map<String, Map<String, Double>>) item.get("estimated_diameter");
                            double diameterMax = metrics.get("kilometers").get("estimated_diameter_max");
                            double diameterMin = metrics.get("kilometers").get("estimated_diameter_min");
                            diameterMedium = (diameterMax + diameterMin) / 2;

                            asteroidList.add(new AsteroidResponse(name, diameterMedium, velocity, date, planet));
                        }
                    }
                }
            }
        }

        if (CollectionUtils.isNotEmpty(asteroidList)) {
            // Sort by diameter
            asteroidList.sort(Comparator.comparing(AsteroidResponse::getDiameter).reversed());

            // Top 3
            asteroidList = asteroidList.subList(0, 3);
        }

        return asteroidList;
    }

}
