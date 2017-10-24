package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.GetDataListener;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

/**
 * Created by cathy on 10/23/2017.
 */

public class MyEventsActivity extends HamburgerActivity {
    TextView eventTextView;
    ArrayList<Event> events;
    LinearLayout buttonLayout;
    int buttonsAdded = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_my_events);
        mainLayout = (ConstraintLayout) findViewById(R.id.my_events_layout);
        events = new ArrayList<>();
        eventTextView = (TextView) findViewById(R.id.textEventList);
        buttonLayout = (LinearLayout) findViewById(R.id.EventButtonLayout);
        for(String str: user.getEventsAttending().keySet()) {
            database.getEvent(str, new GetDataListener(){
                @Override
                public void onStart() {
                    Log.d("STARTED", "Started");
                }

                @Override
                public void onSuccess(DataSnapshot data) {
                    Event e = data.getValue(Event.class);
                    if (e != null) {
                        Log.d("ASSIGN TEMP VALUE", e.getEventName());
                        addToEventList(e);
                    } else {
                        Toast.makeText(MyEventsActivity.this, "This member does not exist!", Toast.LENGTH_SHORT).show();
                        Log.d("ASSIGN TEMP VALUE", "Failure");
                    }
                }
                @Override
                public void onFailed(DatabaseError databaseError) {
                    Log.d("FAILURE", "fail");
                }
            });
            addButton(str);
        }
    }

    /**
     *
     * @param e
     */
    private void addToEventList(Event e) {
        events.add(e);
        eventTextView.setVisibility(View.INVISIBLE);
    }

    private void addButton(String eventName) {
        Button curButton = new Button(this);
        curButton.setId(eventName.hashCode());
        curButton.setText(eventName);
        curButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //find group by
                String eventName = ((Button) v).getText().toString();
                Event e = findEvent(eventName);
                if (e != null) {
                    goToEvent(e);
                }
            }
        });
        buttonLayout.addView(curButton);
        buttonsAdded++;
    }

    /**
     *
     * @param eventName
     */
    private Event findEvent(String eventName) {
        for (Event e: events) {
            if (e.getEventName().equals(eventName)) {
                return e;
            }
        }
        return null;
    }

    /**
     *
     * @param e
     */
    private void goToEvent(Event e) {
        Intent event = new Intent(this, EventActivity.class);
        event.putExtra("curUser", user);
        event.putExtra("curEvent", e);
        System.out.print(event);
        System.out.println(e);
        startActivity(event);
    }

}
