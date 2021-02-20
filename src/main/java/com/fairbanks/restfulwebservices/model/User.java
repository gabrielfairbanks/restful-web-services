package com.fairbanks.restfulwebservices.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class User {

    @NonNull
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Date birthDate;


    private final List<Post> posts = new ArrayList<>();

}
