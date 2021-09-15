package com.jafernandez.nasaapp.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asteroid {

    private Link links;
    private int elementCount;
    private Map<String, Object> nearEarthObjects;

    public Asteroid() { }

    public Asteroid(@JsonProperty(value = "links") Link links,
                    @JsonProperty(value = "element_count") int elementCount,
                    @JsonProperty(value = "near_earth_objects") Map<String, Object> nearEarthObjects) {
        this.links = links;
        this.elementCount = elementCount;
        this.nearEarthObjects = nearEarthObjects;
    }

    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }

    public int getElementCount() {
        return elementCount;
    }

    public void setElementCount(int elementCount) {
        this.elementCount = elementCount;
    }

    public Map<String, Object> getNearEarthObjects() {
        return nearEarthObjects;
    }

    public void setNearEarthObjects(Map<String, Object> nearEarthObjects) {
        this.nearEarthObjects = nearEarthObjects;
    }

}
