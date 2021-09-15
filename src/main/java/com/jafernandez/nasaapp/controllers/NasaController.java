package com.jafernandez.nasaapp.controllers;

import com.jafernandez.nasaapp.models.responses.AsteroidResponse;
import com.jafernandez.nasaapp.services.NasaService;
import com.jafernandez.nasaapp.models.responses.ErrorResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
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
    private ResponseEntity getAsteroids(@RequestParam(required = false, name = "planet") String planet) {

        ResponseEntity responseEntity;

        try {

            if (StringUtils.isEmpty(planet)) {
                // Always indicated the requestParams with required false to control
                // the error and send my own message.
                responseEntity = ResponseEntity
                        .badRequest()
                        .body(new ErrorResponse(HttpStatus.SC_BAD_REQUEST, "The planet param is mandatory."));
            } else {

                List<AsteroidResponse> response = this.nasaService.getAsteroids(planet);

                // Return with empty body because planet does not match with datas.
                responseEntity = CollectionUtils.isEmpty(response)
                        ? ResponseEntity.ok().build()
                        : ResponseEntity.ok().body(response);
            }

        } catch (Exception e) {
            // print a log message
            responseEntity = ResponseEntity
                    .internalServerError()
                    .body(new ErrorResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, "An error occurred while trying to get the asteroids."));
        }

        return responseEntity;
    }

}
