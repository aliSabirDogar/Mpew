package com.radiocodeford.mpew;

/**
 * Created by thiba on 08/05/2017.
 */

public class phoneNumber {

    public static String checkPhoneNumber(String phone, String code)
    {
        if(phone.startsWith("0"))
        {
            phone = code+phone.substring(1);
        }
        if(phone.startsWith( "\\"+code+"0"))
        {
            phone = phone.replaceFirst( "\\"+code+"0",  "\\"+code);
        }

        return phone;
    }
}
