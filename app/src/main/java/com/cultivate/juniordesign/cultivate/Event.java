package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emilyhuskins on 9/29/17.
 */

public class Event implements Parcelable{
    private DatabaseReference mDatabase;



    String eventName;
    String eventGroup;
    String location;
    int dayOfYear;
    int year;
    int timeDayStart;
    int timeDayEnd;
    String allDay;
    Map<String, Boolean> peopleAttending;
    Map<String, Boolean> peopleNotAttending;

    public Event(String meventName, String meventGroup, String mlocation, int mdayOfYear, int myear, int mtimeDayStart, int mtimeDayEnd) {
        eventName = meventName;
        eventGroup = meventGroup;
        location = mlocation;
        dayOfYear = mdayOfYear;
        year = myear;
        timeDayStart = mtimeDayStart; //-1 is allDay
        timeDayEnd = mtimeDayEnd; //-1 is allDay
        allDay = "false";
        if (timeDayStart == timeDayEnd && timeDayStart == 0) {
            allDay = "true";
        }

        peopleAttending = new HashMap<String, Boolean>();
        peopleNotAttending = new HashMap<String, Boolean>();
    }

    public Event(){
        peopleAttending = new HashMap<String, Boolean>();
        peopleNotAttending = new HashMap<String, Boolean>();
    }

    private Event(Parcel in) {
        this(in.readString(), in.readString(), in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        allDay = in.readString();
        List<String> attendingList = new ArrayList<String>();
        List<String> notAttendList = new ArrayList<String>();

        in.readList(attendingList, null);
        in.readList(notAttendList, null);
        for (String x: attendingList) {
            peopleAttending.put(x, Boolean.TRUE);
        }
        for (String x: notAttendList) {
            peopleNotAttending.put(x, Boolean.TRUE);
        }
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventName);
        dest.writeString(eventGroup);
        dest.writeString(location);
        dest.writeInt(dayOfYear);
        dest.writeInt(year);
        dest.writeInt(timeDayStart);
        dest.writeInt(timeDayEnd);
        dest.writeString(allDay);

        if (peopleAttending == null) {
            peopleAttending = new HashMap<String, Boolean>();
        }
        if (peopleNotAttending == null){
            peopleNotAttending = new HashMap<String, Boolean>();
        }
        dest.writeList(new ArrayList(peopleAttending.keySet()));
        dest.writeList(new ArrayList(peopleNotAttending.keySet()));
    }

    public static final Parcelable.Creator<Event> CREATOR
            = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventGroup() {
        return eventGroup;
    }

    public void setEventGroup(String eventGroup) {
        this.eventGroup = eventGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Boolean> getPeopleAttending() {
        return peopleAttending;
    }

    public void setPeopleAttending(Map<String, Boolean> peopleAttending) {
        this.peopleAttending = peopleAttending;
    }

    public Map<String, Boolean> getPeopleNotAttending() {
        return peopleNotAttending;
    }

    public void setPeopleNotAttending(Map<String, Boolean> peopleNotAttending) {
        this.peopleNotAttending = peopleNotAttending;
    }

    public void addPersonAttending(Account user) {
        peopleAttending.put(user.getEmail(), Boolean.TRUE);
    }

    public void addPersonNotAttending(Account user) {
        peopleNotAttending.put(user.getEmail(), Boolean.TRUE);
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTimeDayStart() {
        return timeDayStart;
    }

    public void setTimeDayStart(int timeDayStart) {
        this.timeDayStart = timeDayStart;
    }

    public int getTimeDayEnd() {
        return timeDayEnd;
    }

    public void setTimeDayEnd(int timeDayEnd) {
        this.timeDayEnd = timeDayEnd;
    }

    public String getAllDay() {
        return allDay;
    }

    public void setAllDay(String allDay) {
        this.allDay = allDay;
    }
}

