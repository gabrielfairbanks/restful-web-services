package com.fairbanks.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_ID")
    @SequenceGenerator(name = "POST_ID", sequenceName = "POST_ID_SEQUENCE", allocationSize = 1)
    private Integer id;
    private String description;
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private User user;
}
