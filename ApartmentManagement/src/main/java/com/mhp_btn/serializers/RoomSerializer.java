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
public class RoomSerializer {
    private static String KEY = "ROOM_FILTER";
    private static String[] LIST_FIELDS = {"id","roomNumber"};
    

    public static MappingJacksonValue RoomList(Object rooms){
        MappingJacksonValue values = new MappingJacksonValue(rooms);
        values.setFilters(new SimpleFilterProvider().addFilter(KEY
                , SimpleBeanPropertyFilter.filterOutAllExcept(LIST_FIELDS)));
        return values;
    }

    /**
     * @return the KEY
     */
    public static String getKEY() {
        return KEY;
    }

    /**
     * @return the LIST_FIELDS
     */
    public static String[] getLIST_FIELDS() {
        return LIST_FIELDS;
    }
    
}
