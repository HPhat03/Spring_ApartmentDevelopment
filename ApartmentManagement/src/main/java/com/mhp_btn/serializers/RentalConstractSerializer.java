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
public class RentalConstractSerializer {
    private static String KEY = "CONSTRACT_FILTER";
    private static String[] LIST_FIELDS = {"id","resident", "room"};
    public static MappingJacksonValue RentalConstractList(Object constract){
        MappingJacksonValue values = new MappingJacksonValue(constract);
        SimpleFilterProvider provider = new SimpleFilterProvider();
        provider.addFilter(KEY, SimpleBeanPropertyFilter.filterOutAllExcept(LIST_FIELDS));
        provider.addFilter(UserSerializer.getKEY(), SimpleBeanPropertyFilter.filterOutAllExcept(UserSerializer.getLIST_FIELDS()));
        provider.addFilter(RoomSerializer.getKEY(), SimpleBeanPropertyFilter.filterOutAllExcept(RoomSerializer.getLIST_FIELDS()));
        values.setFilters(provider);
        return values;
    }
    
    public static MappingJacksonValue RentalConstractDetail(Object constract){
        MappingJacksonValue values = new MappingJacksonValue(constract);
        SimpleFilterProvider provider = new SimpleFilterProvider();
        provider.addFilter(KEY, SimpleBeanPropertyFilter.serializeAll());
        provider.addFilter(UserSerializer.getKEY(), SimpleBeanPropertyFilter.filterOutAllExcept(UserSerializer.getLIST_FIELDS()));
        provider.addFilter(RoomSerializer.getKEY(), SimpleBeanPropertyFilter.filterOutAllExcept(RoomSerializer.getLIST_FIELDS()));
        provider.addFilter(ServiceSerializer.getKEY(), SimpleBeanPropertyFilter.serializeAll());
        values.setFilters(provider);
        return values;
    }
    
    public static String getKEY(){
            return KEY;
    }
    
    public static String[] getLIST_FIELDS(){
        return LIST_FIELDS;
    }
    
}
