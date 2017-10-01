package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by emilyhuskins on 9/29/17.
 */

public class Event implements Parcelable{



    String eventName;
    String eventGroup;
    String location;

    List<String> peopleAttending;
    List<String> peopleNotAttending;

    public Event(String meventName, String meventGroup, String mlocation) {
        eventName = meventName;
        eventGroup = meventGroup;
        location = mlocation;
    }

    public Event(){

    }

    private Event(Parcel in) {
        this(in.readString(), in.readString(),in.readString());
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventName);

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

    public void addPersonAttending(Account user) {
        peopleAttending.add(user.getEmail());
    }

    public void addPersonNotAttending(Account user) {
        peopleNotAttending.add(user.getEmail());
    }
}

