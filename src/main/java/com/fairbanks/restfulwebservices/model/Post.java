package com.fairbanks.restfulwebservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private Date timestamp;
}
