package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
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
    Map<String, Boolean> peopleAttending;
    Map<String, Boolean> peopleNotAttending;

    public Event(String meventName, String meventGroup, String mlocation) {
        eventName = meventName;
        eventGroup = meventGroup;
        location = mlocation;
        peopleAttending = new HashMap<String, Boolean>();
        peopleNotAttending = new HashMap<String, Boolean>();
    }

    public Event(){
        peopleAttending = new HashMap<String, Boolean>();
        peopleNotAttending = new HashMap<String, Boolean>();
    }

    private Event(Parcel in) {
        this(in.readString(), in.readString(), in.readString());
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
}

