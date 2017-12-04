package com.cultivate.juniordesign.cultivate;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
        Log.d("ALL DAY TIME STRING", format.format(date.getTime()));
        return format.format(date.getTime()) + ", All Day";
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
            str += format.format(start.getTime());
            str += " to ";
            str += format.format(end.getTime());
        } else {
            str += format.format(start.getTime());
            str += " to ";
            SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm aa");
            str += timeOnly.format(end.getTime());
        }
        Log.d("TIMED STRING", str);
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
    public static Calendar userInputToCalendar(String month, int day, int year, int hour, int minute, String amPm) {
        Calendar date = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        Date aDate;
        try {
            aDate = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
        } catch (ParseException e) {
            Log.d("BAD MONTH", month + " is not a valid month!");
            return null;
        }
        cal.setTime(aDate);
        if (amPm != null) {
            if (amPm.equals("pm")) { //CHECK that string is accurate; check that hour is necessary
                hour += 12;
            }
        }
        int intMonth = cal.get(Calendar.MONTH);
        if (amPm != null) { //not allDay
            date.set(year, intMonth, day, hour, minute);
        } else { //allDay
            date.set(year, intMonth, day);
        }
        return date;
    }

    /**
     *
     * @param date
     * @return
     */
    public static boolean isToday(Calendar date) {
        Calendar now = Calendar.getInstance();
        if (now == null || date == null)
            return false;
        return (now.get(Calendar.YEAR) == date.get(Calendar.YEAR)
                && now.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR));
    }

    /**
     *
     * @param date
     * @return
     */
    public static boolean isLaterThisWeek(Calendar date) {
        Calendar now = Calendar.getInstance();
        if (now == null || date == null)
            return false;
        //if it's earlier it's not later
        if (now.get(Calendar.DAY_OF_YEAR) > date.get(Calendar.DAY_OF_YEAR)) {
            return false;
        }
        return (now.get(Calendar.YEAR) == date.get(Calendar.YEAR)
                && now.get(Calendar.WEEK_OF_YEAR) == date.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     *
     * @param date
     * @return
     */
    public static boolean isNextWeek(Calendar date) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.WEEK_OF_YEAR, 1);
        if (now == null || date == null)
            return false;
        if (now.get(Calendar.DAY_OF_YEAR) > date.get(Calendar.DAY_OF_YEAR)) {
            return false;
        }
        return (now.get(Calendar.YEAR) == date.get(Calendar.YEAR)
                && now.get(Calendar.WEEK_OF_YEAR) == date.get(Calendar.WEEK_OF_YEAR));
    }
 }
