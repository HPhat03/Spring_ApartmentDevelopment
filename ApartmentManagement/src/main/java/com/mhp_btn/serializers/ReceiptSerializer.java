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
public class ReceiptSerializer {
    private static String KEY = "RECEIPT_FILTER";
    
    public static MappingJacksonValue ReceiptList(Object users){
        MappingJacksonValue values = new MappingJacksonValue(users);
        String[] fields = {"id", "name", "createdDate"};
        values.setFilters(new SimpleFilterProvider().addFilter(
                KEY,
                SimpleBeanPropertyFilter.filterOutAllExcept(fields)
                ));
        return values;
    }
    
    public static MappingJacksonValue ReceiptDetail(Object receipt){
        MappingJacksonValue values = new MappingJacksonValue(receipt);
        values.setFilters(new SimpleFilterProvider().addFilter(
                KEY,
                SimpleBeanPropertyFilter.serializeAll()));
        return values;
    }
}
