package org.zerock.web.util;

public class Converter {

    public static int getInt(String value, int defaultValue){
        try
        {
            return Integer.parseInt(value);
        }catch (Exception e){
            return defaultValue;
        }
    }
}
