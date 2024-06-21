/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.serializers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Getter;
import org.springframework.http.converter.json.MappingJacksonValue;

/**
 *
 * @author Admin
 */
@Getter
public class ReportSerializer {
    private static String KEY = "REPORT_FILTER";
    private static String DETAIL = "DETAIL_REPORT_FILTER";
    private static String[] LIST_FIELDS = {"id","title","createdDate"};
    

    public static MappingJacksonValue ReportList(Object report){
        MappingJacksonValue values = new MappingJacksonValue(report);
        values.setFilters(new SimpleFilterProvider().addFilter(KEY
                , SimpleBeanPropertyFilter.filterOutAllExcept(LIST_FIELDS)));
        return values;
    }
    
    public static MappingJacksonValue ReportDetail(Object report){
        MappingJacksonValue values = new MappingJacksonValue(report);
        values.setFilters(new SimpleFilterProvider().addFilter(KEY
                , SimpleBeanPropertyFilter.serializeAll()).addFilter(DETAIL, SimpleBeanPropertyFilter.serializeAll()));
        return values;
    }
}
