package com.example.backend.models.enums;

public enum ToughnessEnum {

    /**
     * Check
     * https://www.bergfreunde.eu/alpine-grades-calculator/#:~:text=white%20path%20markings.-,SAC%20hiking%20scale,-LEVEL
     * for more info on some sample scales.
     */

    EASY("Well developed, signposted and marked path.","Easy"),
    MODERATE("Continuous route and passage marking","Moderate hiking"),
    AVERAGE("Footpath usually available. Exposed places secured with ropes/chains.","Average walking"),
    DIFFICULT("Path might not be available. Sometimes need to use hands to keep going.","Difficult walking"),
    ALPINE("Individual, simple climbing sections often with no path.","Alpine walking to climbing"),
    CHALLENGING_ALPINE("No path, unmarked. Hard climbing.","Challenging alpine climbing");

    private final String description;

    private final String simpleName;

    ToughnessEnum(String description, String simpleName) {
        this.description = description;
        this.simpleName = simpleName;
    }

    public String getDescription() {
        return description;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
