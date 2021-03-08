package com.fairbanks.restfulwebservices.filtering;

import com.fairbanks.restfulwebservices.model.FilteringExampleBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public FilteringExampleBean retrieveBean(){
        return new FilteringExampleBean("value1", "value2","value3");
    }

    @GetMapping("/filtering-list")
    public List<FilteringExampleBean> retrieveListOfBeans(){
        return Arrays.asList(new FilteringExampleBean("value1", "value2","value3"),new FilteringExampleBean("value4", "value5","value6"));
    }
}
