package com.leeo.fitlog;

import java.util.logging.LogRecord;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Formatter;

public class FitlogFormatter extends Formatter {
    
    private java.util.Date dat;
    //private java.text.DateFormat formatter;
    //rivate static final java.lang.String format = "yyyy-MM-dd HH:mm:ss";
    String unixTime;
    
    public FitlogFormatter() {
        unixTime = String.valueOf(System.currentTimeMillis());
    }
    
    @Override
    public String format(LogRecord record) {
        
        //if(formatter==null) formatter = new java.text.SimpleDateFormat(format);
        //dat.setTime(record.getMillis());
        //unixTime = String.valueOf(System.currentTimeMillis());
        unixTime = String.valueOf(System.currentTimeMillis());
        return String.format("%s,%s%n", unixTime, record.getMessage());
    }

}