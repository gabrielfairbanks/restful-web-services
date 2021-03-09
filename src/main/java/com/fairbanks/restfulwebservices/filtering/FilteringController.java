package com.fairbanks.restfulwebservices.filtering;

import com.fairbanks.restfulwebservices.model.FilteringExampleBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveBean() {
        FilteringExampleBean filteringExampleBean = new FilteringExampleBean("value1", "value2", "value3");
        return filterBeanAttributes(filteringExampleBean,"attribute1", "attribute3");
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfBeans() {
        List<FilteringExampleBean> filteringExampleBeans = Arrays.asList(new FilteringExampleBean("value1", "value2", "value3"), new FilteringExampleBean("value4", "value5", "value6"));
        return filterBeanAttributes(filteringExampleBeans,"attribute2", "attribute3");
    }

    private MappingJacksonValue filterBeanAttributes(Object bean, String... filterAttributes) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(filterAttributes);

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilteringExampleBeanFilter", simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
