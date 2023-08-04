package com.example.partialJsonPOC.Atlas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
public class AtlasResponse {
    String replNumber;
    String gtin;
    String location;
    String type;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AtlasResponse(@JsonProperty("replNumber") String replNumber, @JsonProperty("gtin") String gtin,@JsonProperty("location") String location) {
        this.replNumber = replNumber;
        this.gtin = gtin;
        this.location = location;
    }
}

