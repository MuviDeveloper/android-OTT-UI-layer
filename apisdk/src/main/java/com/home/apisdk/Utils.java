package com.home.apisdk;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MUVI on 11/16/2017.
 */

public class Utils {

    public static  String DATETIME_FORMAT = " MM/dd/yyyy HH:mm:ss";
    public static String getCurrentTimeStamp(){
        SimpleDateFormat s = new SimpleDateFormat(DATETIME_FORMAT);
        String format = s.format(new Date());
        return format;
    }

    public static final OkHttpRequester requester = new OkHttpRequester();

    public static boolean checkForNull (String value, JSONObject obj){
        return ((obj.has(value)) && obj.optString(value).trim() != null && !obj.optString(value).trim().isEmpty() && !obj.optString(value).trim().equals(value) && !obj.optString(value).trim().matches("")) ;
    }

    public static String getNotNullValue (String value, JSONObject obj){
        if(checkForNull(value,obj)){
            return obj.optString(value);
        }else
            return "";
    }


}
