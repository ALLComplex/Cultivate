package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by emilyhuskins on 9/6/17.
 */

public class Account implements Parcelable {


    String name;
    String phone;
    String email;
    List<String> memberGroups;
    List<String> manageGroups;

    public Account(String mname, String memail, String mphone) {
        name = mname;
        phone = mphone;
        email = memail;
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

    public List<String> getMemberGroups() {
        return memberGroups;
    }

    public void setMemberGroups(List<String> memberGroups) {
        this.memberGroups = memberGroups;
    }

    public List<String> getManageGroups() {
        return manageGroups;
    }

    public void setManageGroups(List<String> manageGroups) {
        this.manageGroups = manageGroups;
    }

    public void becomeAdmin(Group group){
        manageGroups.add(group.getGroupName());
        group.addAdmin(email);
    }

    public void becomeMember(Group group){
        memberGroups.add(group.getGroupName());
        group.addMember(email);
    }


}
