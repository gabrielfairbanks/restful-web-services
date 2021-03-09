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

    @GetMapping(value="person/header", headers="X-API-VERSION=1")
    public PersonV1 personV1header(){
        return new PersonV1("Claudio Fairbanks");
    }

    @GetMapping(value="person/header", headers="X-API-VERSION=2")
    public PersonV2 personV2header(){
        return new PersonV2(new Name("Claudio", "Fairbanks"));
    }

    @GetMapping(value="person/produces", produces="application/vnd.company.app-v1+json")
    public PersonV1 personV1produces(){
        return new PersonV1("Claudio Fairbanks");
    }

    @GetMapping(value="person/produces", produces="application/vnd.company.app-v2+json")
    public PersonV2 personV2produces(){
        return new PersonV2(new Name("Claudio", "Fairbanks"));
    }
}
