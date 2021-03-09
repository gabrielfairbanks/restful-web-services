package com.fairbanks.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Claudio Fairbanks");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Claudio", "Fairbanks"));
    }

    @GetMapping(value="person/param", params="version=1")
    public PersonV1 personV1param(){
        return new PersonV1("Claudio Fairbanks");
    }

    @GetMapping(value="person/param", params="version=2")
    public PersonV2 personV2param(){
        return new PersonV2(new Name("Claudio", "Fairbanks"));
    }
}
