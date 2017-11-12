package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.cultivate.juniordesign.cultivate.Utility;

import org.w3c.dom.Text;

/**
 * Created by Paul on 9/10/2017.
 */

public class EventActivity extends HamburgerActivity {
    Event event;
    TextView textTitle;
    TextView textName;
    TextView textTime;
    TextView textLocation;
    TextView textGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        user = getIntent().getParcelableExtra("curUser");
        mainLayout = (ConstraintLayout) findViewById(R.id.event_layout);
        event = getIntent().getParcelableExtra("curEvent");
        textTitle = (TextView) findViewById(R.id.textView2);
        textTitle.setText(event.getEventName());
        textTime = (TextView) findViewById(R.id.eventTime);
        if (event.getAllDay().equals("true")) {
            textTime.setText(Utility.displayDateTime(Utility.digitToCalendar(event.getDayOfYear(), event.getYear(), event.getTimeDayStart())));
        } else {
            textTime.setText(Utility.displayDateTime(Utility.digitToCalendar(event.getDayOfYear(), event.getYear(), event.getTimeDayStart()),
                    Utility.digitToCalendar(event.getDayOfYear(), event.getYear(), event.getTimeDayEnd())));
        }
        textName = (TextView) findViewById(R.id.eventName);
        textName.setText(event.getEventName());
        textLocation = (TextView) findViewById(R.id.eventLocation);
        textLocation.setText(event.getLocation());
        textGroup = (TextView) findViewById(R.id.eventGroup);
        textGroup.setText(event.getEventGroup());
    }

    public void goToMarkAsGoing(View v) {
        if (event.getPeopleAttending().containsKey(user.getName())) {
            //throw a toast\ "you are already attending the event!!"
            Log.d("Already Attending", "User is already attending this event");
            return;
        } else {
            user.attendEvent(event);
            updateUser(user);
            updateEvent(event);
            Log.d("Attending", "User is attending this event");
        }
    }

    public void goToMarkAsNotGoing(View v) {
        if (event.getPeopleNotAttending().containsKey(user.getName())) {
            Log.d("Already Not Attending", "User is already not attending this event");
            return;
        } else {
            user.notAttendEvent(event);
            updateUser(user);
            updateEvent(event);
            Log.d("Not Attending", "User is not attending this event");

        }
    }

    public void goToAttendance(View v) {
        Intent attendance = new Intent(this, AttendanceActivity.class);
        attendance.putExtra("curUser", user);
        attendance.putExtra("curEvent", event);
        startActivity(attendance);
    }
}
