package com.doodlemars.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UtilFunc {
	
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sd = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        return sd.format(date);
	}

}
