/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.serializers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

/**
 *
 * @author Admin
 */
public class UserSerializer {
    
    private static String KEY = "USER_FILTER";
    
    public static MappingJacksonValue UserList(Object users){
        MappingJacksonValue values = new MappingJacksonValue(users);
        String[] fields = {"id", "name", "role"};
        values.setFilters(new SimpleFilterProvider().addFilter(
                KEY,
                SimpleBeanPropertyFilter.filterOutAllExcept(fields)
                ));
        return values;
    }
    
    public static MappingJacksonValue UserDetail(Object users){
        MappingJacksonValue values = new MappingJacksonValue(users);
        values.setFilters(new SimpleFilterProvider().addFilter(
                KEY,
                SimpleBeanPropertyFilter.serializeAll()
                ));
        return values;
    }
}
