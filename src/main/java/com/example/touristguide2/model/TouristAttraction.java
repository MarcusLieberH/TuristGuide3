package com.example.touristguide2.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private Long id;
    private String name;
    private String description;
    private List<TouristTags> tags;
    private TouristTowns towns;
    private String image;  // Dette er nu kun en URL, ikke en filsti

    // Constructor
    public TouristAttraction() {
        this.name = name;
        this.description = description;
        this.towns = towns;
        this.tags = (tags != null) ? tags : new ArrayList<>();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TouristTags> getTags() {
        return tags;
    }

    public void setTags(List<TouristTags> tags) {
        this.tags = tags;
    }

    public TouristTowns getTowns() {
        return towns;
    }

    public void setTown(TouristTowns towns) {
        this.towns = towns;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;  // Sæt webadressen her
    }

    public void addTag(TouristTags tag) {
        tags.add(tag);
    }
}
