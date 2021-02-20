package com.fairbanks.restfulwebservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Post {
    private Integer id;
    private String description;
    private Date timestamp;
}
