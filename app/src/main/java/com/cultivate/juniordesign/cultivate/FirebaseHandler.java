package com.cultivate.juniordesign.cultivate;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by emilyhuskins on 10/15/17.
 */

public class FirebaseHandler {


    private static DatabaseReference mDatabase;

    public FirebaseHandler() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (mDatabase == null) {
            System.out.print("hi");
        }
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

    public void getAccount(String email, final GetDataListener listener){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(email).getRef();
        listener.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TEST", "SUCCESS!");
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FAILURE", "Failed to read value.", databaseError.toException());
                listener.onFailed(databaseError);
            }
        });
    }

    public void getGroup(String groupName, final GetDataListener listener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("groups").child(groupName).getRef();
        listener.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TEST", "SUCCESS!");
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FAILURE", "Failed to read value.", databaseError.toException());
                listener.onFailed(databaseError);
            }
        });
    }

    public void getEvent(String eventName, final GetDataListener listener){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("events").child(eventName).getRef();
        listener.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TEST", "SUCCESS!");
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FAILURE", "Failed to read value.", databaseError.toException());
                listener.onFailed(databaseError);
            }
        });
    }

}
