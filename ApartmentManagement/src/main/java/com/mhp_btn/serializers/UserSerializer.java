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
    private static String[] LIST_FIELDS = {"id","name", "role", "avatar"};
    public static MappingJacksonValue UserList(Object users){
        MappingJacksonValue values = new MappingJacksonValue(users);
        values.setFilters(new SimpleFilterProvider().addFilter(KEY
                , SimpleBeanPropertyFilter.filterOutAllExcept(LIST_FIELDS)));
        return values;
    }
    
    public static MappingJacksonValue UserDetail(Object users){
        MappingJacksonValue values = new MappingJacksonValue(users);
        values.setFilters(new SimpleFilterProvider().addFilter(getKEY(),
                SimpleBeanPropertyFilter.serializeAll()
                ));
        return values;
    }
    
//    public static ApartmentUser applyPatch(JsonPatch patchData, ApartmentUser user) throws JsonPatchException, IllegalArgumentException, JsonProcessingException{
//        ObjectMapper om = new ObjectMapper();
//        JsonNode patched = patchData.apply(om.convertValue(user, JsonNode.class));
//        return om.treeToValue(patched, ApartmentUser.class);
//    }

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
