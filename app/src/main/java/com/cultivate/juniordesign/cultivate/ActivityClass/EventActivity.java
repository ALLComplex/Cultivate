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
    private Event event = null;
    TextView textView2;
    TextView textLocation;
    TextView textGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        mainLayout = (ConstraintLayout) findViewById(R.id.event_layout);
        event = getIntent().getParcelableExtra("curEvent");
        setContentView(R.layout.activity_event);
        textView2 = (TextView) findViewById(R.id.eventName);
        textView2.setText(event.getEventName());
        textLocation = (TextView) findViewById(R.id.eventLocation);
        textLocation.setText(event.getLocation());
        textLocation = (TextView) findViewById(R.id.eventGroup);
        textLocation.setText(event.getEventGroup());
        //textLocation = (TextView) findViewById(R.id.eventTime);
        //textLocation.setText(event.);
    }

    /**
     * Queries the database to determine if the user is an admin for the group this event is a part of.
     * If so, return true
     * @return boolean whether or not the user is an admin
     */
    private boolean checkIfAdmin() {
        return false;
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
}
