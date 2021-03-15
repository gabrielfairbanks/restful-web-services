package com.fairbanks.restfulwebservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "Details about users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID")
    @SequenceGenerator(name = "USER_ID", sequenceName = "USER_ID_SEQUENCE", allocationSize = 1)
    @NonNull
    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @ApiModelProperty(notes = "Name should have at least 2 characters")
    @NonNull
    private String name;

    @Past(message = "Birth date needs to be in the past, is the user not born yet, punk?")
    @ApiModelProperty(notes = "Birth date should be in the past.")
    @NonNull
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

}
