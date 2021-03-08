package com.fairbanks.restfulwebservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "Details about users")
public class User {

    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @ApiModelProperty(notes = "Name should have at least 2 characters")
    private String name;

    @Past(message = "Birth date needs to be in the past, is the user not born yet, punk?")
    @ApiModelProperty(notes = "Birth date should be in the past.")
    private Date birthDate;


    private List<Post> posts = new ArrayList<>();

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
