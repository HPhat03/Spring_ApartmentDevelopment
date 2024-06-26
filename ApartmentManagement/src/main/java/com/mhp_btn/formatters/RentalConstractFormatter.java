package com.mhp_btn.formatters;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentRoom;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RentalConstractFormatter implements Formatter<ApartmentRentalConstract> {
    @Override
    public ApartmentRentalConstract parse(String id, Locale locale) throws ParseException {
        ApartmentRentalConstract c = new ApartmentRentalConstract();
        c.setId(Integer.parseInt(id));
        return  c;
    }

    @Override
    public String print(ApartmentRentalConstract r, Locale locale) {
        return String.valueOf(r.getId());
    }
}
