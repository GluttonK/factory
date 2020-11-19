package com.nobugs.util;

import java.text.Format;
import java.text.SimpleDateFormat;

public class TimeStamp {

    private final static Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getTimeStamp(){
        return dateFormat.format(System.currentTimeMillis());
    }

}
