package com.cloud.platform.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String SimpleFormatDate(Date date){
        if(date==null)
            return null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date paresDate(String dateStr) throws ParseException {
        DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parser.parse(dateStr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,1);
        return date;
    }

}
