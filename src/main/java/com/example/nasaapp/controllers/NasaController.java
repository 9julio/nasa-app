package com.example.nasaapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NasaController {

    @GetMapping("/asteroids")
    private ResponseEntity<String> createTransaction() {
        return ResponseEntity.status(200).body("Hello world!");
    }

}
