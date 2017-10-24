package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.GetDataListener;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

/**
 * Created by cathy on 10/23/2017.
 */

public class MyEventsActivity extends HamburgerActivity {
    String eventText;
    TextView eventTextView;
    ArrayList<Event> events;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_my_events);
        mainLayout = (ConstraintLayout) findViewById(R.id.my_events_layout);
        events = new ArrayList<>();
        eventText = "";
        eventTextView = (TextView) findViewById(R.id.textEventList);
        for(String str: user.getEventsAttending().keySet()) {
            FirebaseHandler db = new FirebaseHandler();
            db.getEvent(str, new GetDataListener(){
                @Override
                public void onStart() {
                    Log.d("STARTED", "Started");
                }

                @Override
                public void onSuccess(DataSnapshot data) {
                    Event e = (Event) data.getValue(Event.class);
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
        }
    }

    private void addToEventList(Event e) {
        events.add(e);
        eventText = eventText.concat("\n\n");
        eventText = eventText.concat(e.getEventName());
        eventText = eventText.concat(" at ");
        eventText = eventText.concat(e.getLocation());
        eventText = eventText.concat(" with " + e.getEventGroup());
        eventTextView.setText(eventText);
    }

}
