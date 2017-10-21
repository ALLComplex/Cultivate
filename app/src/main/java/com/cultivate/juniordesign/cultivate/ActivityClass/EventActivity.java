package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;

/**
 * Created by Paul on 9/10/2017.
 */

public class EventActivity extends HamburgerActivity {
    private Account user = null;
    private Event event = null;
    TextView textView2;
    TextView textLocation;
    TextView textGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        event = getIntent().getParcelableExtra("curEvent");
        setContentView(R.layout.activity_event);
        textView2 = (TextView) findViewById(R.id.eventName);
        textView2.setText(event.getEventName());
        textLocation = (TextView) findViewById(R.id.eventLocation);
        textLocation.setText(event.getLocation());
        textLocation = (TextView) findViewById(R.id.eventGroup);
        textLocation.setText(event.getEventGroup());

    }

    /**
     * Queries the database to determine if the user is an admin for the group this event is a part of.
     * If so, return true
     * @return boolean whether or not the user is an admin
     */
    private boolean checkIfAdmin() {
        return false;
    }

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void openHamburgerBar(View v) {
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.event_layout);
        super.openHamburgerBar(mainLayout, v, user);
    }

    public void goToStub(View v) {
        Intent stub = new Intent(this, StubActivity.class);
        stub.putExtra("curUser", user);
        startActivity(stub);
    }

    public void goToEvent(View v) {
        Intent event = new Intent(this, EventActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void goToProfile(View v) {
        Intent profile = new Intent(this, ProfileActivity.class);
        profile.putExtra("curUser", user);
        startActivity(profile);
    }

    public void goToGroup(View v) {
        Intent group = new Intent(this, MyGroupsActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    public void goToMarkAsGoing(View v) {
        if (event.getPeopleAttending().containsKey(user.getName())) {
            //throw a toast "you are already attending the event!!"
            Log.d("Already Attending", "User is already attending this event");
            return;
        } else {
            user.attendEvent(event);
            Log.d("Attending", "User is attending this event");
        }
    }

    public void goToMarkAsNotGoing(View v) {
        if (event.getPeopleNotAttending().containsKey(user.getName())) {
            Log.d("Already Not Attending", "User is already not attending this event");
            return;
        } else {
            user.notAttendEvent(event);
            Log.d("Not Attending", "User is not attending this event");

        }
    }

    /*
    goes to the login screen; clears user data
     */
    public void goToLogout(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void notImplemented(View v) {
        Toast.makeText(EventActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

}
