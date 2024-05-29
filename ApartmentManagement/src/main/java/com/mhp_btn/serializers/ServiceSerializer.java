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
public class ServiceSerializer {
    private static String KEY = "SERVICE_FILTER";
    
    private static String[] LIST_FIELDS = {"id","name", "role"};
    public static MappingJacksonValue UserList(Object services){
        MappingJacksonValue values = new MappingJacksonValue(services);
        values.setFilters(new SimpleFilterProvider().addFilter(KEY
                , SimpleBeanPropertyFilter.filterOutAllExcept(LIST_FIELDS)));
        return values;
    }
    public static String getKEY(){
        return KEY;
    }
}
