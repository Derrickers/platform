package com.cloud.platform.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String SimpleFormatDate(Date date){
        if(date==null)
            return null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
