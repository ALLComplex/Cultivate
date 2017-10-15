package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emilyhuskins on 9/6/17.
 */

public class Account implements Parcelable {
    private DatabaseReference mDatabase;


    String name;
    String phone;
    String email;
    Map<String, Boolean> memberGroups = new HashMap<String, Boolean>();
    Map<String, Boolean> manageGroups = new HashMap<String, Boolean>();
    Map<String, Boolean> events = new HashMap<>();

    public Account(String mname, String memail, String mphone) {
        name = mname;
        phone = mphone;
        email = memail;
    }



    private Account(Parcel in) {
        this(in.readString(), in.readString(), in.readString());
        List<String> memberList = new ArrayList<String>();
        List<String> adminList = new ArrayList<String>();

        in.readList(memberList, null);
        in.readList(adminList, null);
        for (String x: memberList) {
           memberGroups.put(x, Boolean.TRUE);
        }
        for (String x: adminList) {
            manageGroups.put(x, Boolean.TRUE);
        }

    }

    public Account() {

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeList(new ArrayList(memberGroups.keySet()));
        dest.writeList(new ArrayList(manageGroups.keySet()));

    }

    public static final Parcelable.Creator<Account> CREATOR
            = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getMemberGroups() {
        return memberGroups;
    }

    public void setMemberGroups(Map<String, Boolean> memberGroups) {
        this.memberGroups = memberGroups;
    }

    public Map<String, Boolean> getManageGroups() {
        return manageGroups;
    }

    public void setManageGroups(Map<String, Boolean> manageGroups) {
        this.manageGroups = manageGroups;

    }


    public void becomeAdmin(Group group){
        manageGroups.put(group.getGroupName(), Boolean.TRUE);
        group.addAdmin(this);
    }

    public void becomeMember(Group group){
        memberGroups.put(group.getGroupName(), Boolean.TRUE);
        group.addMember(this);
    }


}
