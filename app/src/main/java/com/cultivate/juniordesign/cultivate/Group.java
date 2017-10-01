package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilyhuskins on 9/29/17.
 */

public class Group implements Parcelable{


    String groupName;
    String location;
    List<String> groupMembers = new ArrayList<>();
    List<String> groupAdmins = new ArrayList<>();
    List<String> events = new ArrayList<>();

    public Group(String mgroupName, String mlocation, String admin) {
        groupName = mgroupName;
        location = mlocation;
        groupAdmins.add(admin);
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

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<String> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public List<String> getGroupAdmins() {
        return groupAdmins;
    }

    public void setGroupAdmins(List<String> groupAdmins) {
        this.groupAdmins = groupAdmins;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public void addMember(String uid){
        groupMembers.add(uid);
    }

    public void addAdmin(String uid){
        groupAdmins.add(uid);
    }

    public void addEvent(Event event) {
        events.add(event.getEventName());
    }

}

