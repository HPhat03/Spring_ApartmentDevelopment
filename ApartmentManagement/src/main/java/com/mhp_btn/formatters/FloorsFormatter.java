package com.mhp_btn.formatters;

import com.mhp_btn.pojo.ApartmentFloor;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class FloorsFormatter implements Formatter<ApartmentFloor> {

    @Override
    public ApartmentFloor parse(String id, Locale locale) throws ParseException {
        ApartmentFloor c = new ApartmentFloor();
        c.setId(Integer.parseInt(id));
        return  c;
    }

    @Override
    public String print(ApartmentFloor f, Locale locale) {
        return String.valueOf(f.getId());
    }
}
