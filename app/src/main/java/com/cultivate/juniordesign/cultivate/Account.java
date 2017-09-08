package com.cultivate.juniordesign.cultivate;

import java.util.List;

/**
 * Created by emilyhuskins on 9/6/17.
 */

public class Account {

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




}
