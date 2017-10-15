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

public class Group implements Parcelable{
    private DatabaseReference mDatabase;

    String groupName;
    String location;
    Map<String, Boolean> groupMembers = new HashMap<String, Boolean>();
    Map<String, Boolean> groupAdmins = new HashMap<String, Boolean>();
    Map<String, Boolean> events = new HashMap<String, Boolean>();

    public Group(String mgroupName, String mlocation) {
        groupName = mgroupName;
        location = mlocation;
    }

    public Group() {
    }

    private Group(Parcel in) {
        this(in.readString(), in.readString());
        List<String> groupMems = new ArrayList<String>();
        List<String> groupAds = new ArrayList<String>();
        List<String> eventList = new ArrayList<String>();

        in.readList(groupMems, null);
        in.readList(groupAds, null);
        in.readList(eventList, null);

        for (String x: groupMems) {
            groupMembers.put(x, Boolean.TRUE);
        }
        for (String x: groupAds) {
            groupAdmins.put(x, Boolean.TRUE);
        }
        for (String x: eventList) {
            events.put(x, Boolean.TRUE);
        }


    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(groupName);
        dest.writeString(location);
        dest.writeList(new ArrayList(groupMembers.keySet()));
        dest.writeList(new ArrayList(groupAdmins.keySet()));
        dest.writeList(new ArrayList(events.keySet()));

    }

    public static final Parcelable.Creator<Group> CREATOR
            = new Parcelable.Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Boolean> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Map<String, Boolean> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public Map<String, Boolean> getGroupAdmins() {
        return groupAdmins;
    }

    public void setGroupAdmins(Map<String, Boolean> groupAdmins) {
        this.groupAdmins = groupAdmins;
    }

    public Map<String, Boolean> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Boolean> events) {
        this.events = events;
    }


    public void addMember(Account user){
        groupMembers.put(user.getEmail(), Boolean.TRUE);
    }

    public void addAdmin(Account user){
        groupAdmins.put(user.getEmail(), Boolean.TRUE);
    }

    public void addEvent(Event event) {
        events.put(event.getEventName(), Boolean.TRUE);
    }

}

