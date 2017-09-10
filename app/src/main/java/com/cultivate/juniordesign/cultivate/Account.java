package com.cultivate.juniordesign.cultivate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by emilyhuskins on 9/6/17.
 */

public class Account implements Parcelable {

    int accountID;
    String firstName;
    String lastName;
    String email;
    List<Integer> memberGroups;
    List<Integer> manageGroups;

    public Account(String firstName, String lastName, String email) {
        firstName = firstName;
        lastName = lastName;
        email = email;
    }

    public Account(String memail){
        email = memail;
    }

    private Account(Parcel in) {
        this(in.readString());
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


    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getMemberGroups() {
        return memberGroups;
    }

    public void setMemberGroups(List<Integer> memberGroups) {
        this.memberGroups = memberGroups;
    }

    public List<Integer> getManageGroups() {
        return manageGroups;
    }

    public void setManageGroups(List<Integer> manageGroups) {
        this.manageGroups = manageGroups;
    }



}
