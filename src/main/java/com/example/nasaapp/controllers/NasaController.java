package com.example.nasaapp.controllers;

import com.example.nasaapp.models.responses.AsteroidResponse;
import com.example.nasaapp.services.NasaService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NasaController {

    @Autowired
    private NasaService nasaService;

    @GetMapping(value = "/asteroids", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> getAsteroids(@RequestParam(required = false, name = "planet") String planet) {

        ResponseEntity responseEntity;

        try {

            if (StringUtils.isEmpty(planet)) {
                // Always indicated the requestParams with required false to controlate the error and send my own message.
                responseEntity = ResponseEntity
                        .badRequest()
                        .body("The planet param is mandatory.");
            } else {

                List<AsteroidResponse> response = nasaService.getAsteroids(planet);

                // Return with empty body because planet does not match with datas.
                responseEntity = CollectionUtils.isEmpty(response)
                        ? ResponseEntity.ok().build()
                        : ResponseEntity.ok().body(response);
            }

        } catch (Exception e) {
            // print a log message
            responseEntity = ResponseEntity.internalServerError().build();
        }

        return responseEntity;
    }

}
