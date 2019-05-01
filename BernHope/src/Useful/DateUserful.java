/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Useful;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Bruno
 */
public final class DateUserful {
    
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    
    public static long getDiffDates(String currentDate, String previousDate) throws ParseException{
        Date current, previous;
        long diff;
        
            current = convertStringToDate(currentDate);
        previous = convertStringToDate(previousDate);
        
        diff = (current.getTime() - previous.getTime()) + 3600000;
        
        return (diff / 86400000L);
    }

    public static long getDiffDateWithCurrentDate(String previousDate) throws ParseException{
        Date current, previous;
        long diff;
        
        current = new Date();
        previous = convertStringToDate(previousDate);
        
        diff = (current.getTime() - previous.getTime()) + 3600000;
        
        return (diff / 86400000L);
    }
    
    public static Date convertStringToDate(String currentDate) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NOW);
        Date date = format.parse(currentDate);
        return date;
    }
    
    public static Date convertStringToDateWithFormat(String currentDate, String formatDate) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(formatDate);
        Date date = format.parse(currentDate);
        return date;
    }
    
    public static int getYear(String currentDate) throws ParseException{
        Date date = convertStringToDate(currentDate);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);        
        return calendar.get(Calendar.YEAR);
    }
    
    public static int getCurrentMonth(){
        Calendar cal = Calendar.getInstance();
        return Calendar.MONTH;
    }
    
    public static int getMonth(String currentDate) throws ParseException{
        Date date = convertStringToDate(currentDate);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);        
        return calendar.get(Calendar.MONTH);
    }
    
    
}
