package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emilyhuskins on 9/29/17.
 */

public class Group implements Parcelable{


    String groupName;
    String location;
    Map<String, Boolean> groupMembers;
    Map<String, Boolean> groupAdmins;
    Map<String, Boolean> events;

    public Group(String mgroupName, String mlocation, String admin) {
        groupName = mgroupName;
        location = mlocation;

        groupMembers = new HashMap<String, Boolean>();
        groupAdmins = new HashMap<String, Boolean>();
        events = new HashMap<String, Boolean>();
        groupAdmins.put(admin, Boolean.TRUE);
    }

    public Group() {
    }

    private Group(Parcel in) {
        this(in.readString(), in.readString(),in.readString());
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(groupName);

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

