package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emilyhuskins on 9/6/17.
 */

public class Account implements Parcelable {


    String name;
    String phone;
    String email;
    Map<String, Boolean> memberGroups;
    Map<String, Boolean> manageGroups;

    public Account(String mname, String memail, String mphone) {
        name = mname;
        phone = mphone;
        email = memail;
        memberGroups = new HashMap<String, Boolean>();
        manageGroups = new HashMap<String, Boolean>();

    }


    private Account(Parcel in) {
        this(in.readString(), in.readString(), in.readString());
    }

    public Account() {

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);

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
