package com.cultivate.juniordesign.cultivate;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by emilyhuskins on 10/15/17.
 */

public class FirebaseHandler {
    Account user = null;
    Group group = null;
    Event event = null;

    private static DatabaseReference mDatabase;

    public static void FirebaseHandler(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void pushAccountChange(Account account){
        mDatabase.child("users").child(account.getEmail()).setValue(account);
    }

    public void pushGroupChange(Group group){
        mDatabase.child("groups").child(group.getGroupName()).setValue(group);
    }

    public void pushEventChange(Event event){
        mDatabase.child("events").child(event.getEventName()).setValue(event);
    }

    public Account getAccount(String email){

        mDatabase.child("users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getChildren().iterator().next().getValue(Account.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return user;

    }

    public Group getGroup(String ref){

        mDatabase.child("groups").orderByChild("groupName").equalTo(ref).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                group = dataSnapshot.getChildren().iterator().next().getValue(Group.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return group;
    }

    public Event getEvent(String ref){

        mDatabase.child("events").orderByChild("eventName").equalTo(ref).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                event = dataSnapshot.getChildren().iterator().next().getValue(Event.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return event;
    }

}
