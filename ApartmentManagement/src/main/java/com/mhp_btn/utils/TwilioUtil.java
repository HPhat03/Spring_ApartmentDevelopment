/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.utils;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Admin
 */
public class TwilioUtil {
    private static String ACOUNT_ID = "ACb95ee7d47f958676687f4c9c432639f3";
    private static String AUTH_TOKEN = "c7bad489132fd0cc81b72303ae865f5f";
    private static String PHONE_NUMBER = "+13345532554";
    
    public static void SendSMS(String ToPhone, String Content){
        try {
            Twilio.init(ACOUNT_ID,AUTH_TOKEN);
            Message msg = Message.creator(new PhoneNumber(ToPhone), new PhoneNumber(PHONE_NUMBER), Content).create();
            System.out.println(msg.getStatus());
        }
        catch (TwilioException e){
            System.out.println(e.getMessage());
        }
    }
}
