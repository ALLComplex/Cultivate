package com.cultivate.juniordesign;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


/**
 * Created by Cathy on 11/1/2017.
 */
public class Utility {
    //TODO Fill in java docs

    /**
     *
     * @param begin
     * @return
     */
    public static String relevantToCurrentTime(Calendar begin) {
        return relevantToCurrentTime(begin, null);
    }

    /**
     *
     * @param begin start date for the calendar
     * @param end terminating date for the calendar
     * @return
     */
    public static String relevantToCurrentTime(Calendar begin, Calendar end) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        if (now.before(begin)) {
            return "Event hasn't Started";
        }
        if (end == null) {
            //if event has no end then it's an all day event, so same day and month ==> now
            if (begin.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR) && begin.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
                return "Event is now";
            } else {
                return "Event is over";
            }
        } else if (end.after(now)){
            return "Event is over";
        }
        return "Event is now";
    }

    /**
     *
     * @param date
     * @return
     */
    public static String displayDateTime(Calendar date) {
        //event with no end time are considered all day
        SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy");
        return format.format(date.getTime());
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public static String displayDateTime(Calendar start, Calendar end) {
        String str = "";
        SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy h:mm aa");
        if (start.get(Calendar.YEAR) != end.get(Calendar.YEAR) || (start.get(Calendar.MONTH) != end.get(Calendar.MONTH)) || (start.get(Calendar.DAY_OF_MONTH) != end.get(Calendar.DAY_OF_MONTH))) {
            str.concat(format.format(start.getTime()));
            str.concat(" to ");
            str.concat(format.format(end.getTime()));
        } else {
            str.concat(format.format(start.getTime()));
            str.concat(" to ");
            SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm aa");
            str.concat(timeOnly.format(end.getTime()));
        }
        return str;
    }

    /**
     *
     * @param dayOfYear
     * @param year
     * @param timeInterval
     * @return
     */
    public static Calendar digitToCalendar(int dayOfYear, int year, int timeInterval) {
        Calendar date  = Calendar.getInstance();
        date.setLenient(true);
        date.set(Calendar.DAY_OF_YEAR, dayOfYear);
        date.set(Calendar.YEAR, year);
        int minutes = (timeInterval % 4);
        int hours = (timeInterval - minutes) / 4;
        minutes *= 15;
        date.set(Calendar.MINUTE, minutes);
        date.set(Calendar.HOUR_OF_DAY, hours);
        return date;
    }

    /**
     *
     * @param date
     * @return
     */
    public static int[] calendarToDigit(Calendar date) {
        int[] info = new int[3];
        info[0] = date.get(Calendar.DAY_OF_YEAR);
        info[1] = date.get(Calendar.YEAR);
        info[2] = (date.get(Calendar.HOUR_OF_DAY)*4 + date.get(Calendar.MINUTE)/15);
        return info;
    }

    /**
     * Not sure if these will be parameters
     * @param month
     * @param day
     * @param year
     * @param hour
     * @param minute
     * @param amPm
     * @return
     */
    public static Calendar userInputTOCalendar(String month, int day, int year, int hour, int minute, String amPm) {
        Calendar date = Calendar.getInstance();
        //TODO actually set date to given parameters
        return date;
    }
 }
