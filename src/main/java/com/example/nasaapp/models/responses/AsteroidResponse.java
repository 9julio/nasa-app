package com.example.nasaapp.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsteroidResponse {

    private String name;
    private double diameter;
    private String velocity;
    private String date;
    private String planet;

    public AsteroidResponse(String name, double diameter, String velocity, String date, String planet) {
        this.name = name;
        this.diameter = diameter;
        this.velocity = velocity;
        this.date = date;
        this.planet = planet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public String getVelocity() {
        return velocity;
    }

    public void setVelocity(String velocity) {
        this.velocity = velocity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    @Override
    public String toString() {
        return "AsteroidResponse{" +
                "name='" + name + '\'' +
                ", diameter=" + diameter +
                ", velocity='" + velocity + '\'' +
                ", date='" + date + '\'' +
                ", planet='" + planet + '\'' +
                '}';
    }

}
