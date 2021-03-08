package com.fairbanks.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilteringExampleBean {
    private String attribute1;
    private String attribute2;
    @JsonIgnore
    private String attribute3;

}
