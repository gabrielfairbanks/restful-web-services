package com.fairbanks.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonFilter("FilteringExampleBeanFilter")
public class FilteringExampleBean {
    private String attribute1;
    private String attribute2;
    private String attribute3;

}
