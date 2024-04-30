/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.validators;


import com.mhp_btn.pojo.ApartmentUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Admin
 */
@Component
public class PassValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return ApartmentUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ApartmentUser u = (ApartmentUser) target;
        if (!u.getPassword().trim().equals(u.getConfirmPassword())){
            errors.rejectValue("password","user.password.error.notMatchMsg");

        }
    }
    
}
