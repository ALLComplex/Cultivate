package com.cultivate.juniordesign.cultivate.ActivityClass;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.GetDataListener;
import com.cultivate.juniordesign.cultivate.R;
import com.cultivate.juniordesign.cultivate.Utility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MainActivity extends HamburgerActivity {
    private DatabaseReference mDatabase;
    Map<Event, Set<Calendar>> eventsToday;
    Map<Event, Set<Calendar>> eventsThisWeek;
    Map<Event, Set<Calendar>> eventsNextWeek;
    Map<Event, Set<Calendar>> allEvents;
    TextView today;
    TextView todayText;
    TextView nextWeek;
    TextView nextWeekText;
    TextView thisWeek;
    TextView thisWeekText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getIntent().getParcelableExtra("curUser");
        mainLayout = (ConstraintLayout) findViewById(R.id.home_layout);
        today = (TextView) findViewById(R.id.today);
        todayText = (TextView) findViewById(R.id.todayText);
        thisWeek = (TextView) findViewById(R.id.thisWeek);
        thisWeekText = (TextView) findViewById(R.id.thisWeekText);
        nextWeek = (TextView) findViewById(R.id.nextWeek);
        nextWeekText = (TextView) findViewById(R.id.nextWeekText);
        Set<String> events = user.getEventsAttending().keySet();
        eventsToday = new HashMap<>();
        eventsThisWeek = new HashMap<>();
        eventsNextWeek = new HashMap<>();
        allEvents = new HashMap<>();
        for(String str: events) {
            database.getEvent(str, new GetDataListener(){
                @Override
                public void onStart() {
                    Log.d("STARTED", "Started");
                }

                @Override
                public void onSuccess(DataSnapshot data) {
                    Event e = (Event) data.getValue(Event.class);
                    if (e != null) {
                        Log.d("ASSIGN TEMP VALUE", e.getLocation());
                        addToEventSet(e);
                    } else {
                        Toast.makeText(MainActivity.this, "This event does not exist!", Toast.LENGTH_SHORT).show();
                        Log.d("ASSIGN TEMP VALUE", "Failure");
                    }
                }
                @Override
                public void onFailed(DatabaseError databaseError) {
                    Log.d("FAILURE", "fail");
                }
            });
        }
        //TODO add time logically to text
        if (eventsToday.size() == 0) {
            today.setVisibility(View.GONE);
            todayText.setVisibility(View.GONE);
        } else {
            String todayStr = "";
            for(Event e: eventsToday.keySet()) {
                todayStr = todayStr.concat(e.getEventName());
                todayStr = todayStr.concat(" at ");
                todayStr = todayStr.concat(e.getLocation());
                todayStr =todayStr.concat("\n");
            }
            todayText.setText(todayStr);
        }
        if (eventsThisWeek.size() == 0) {
            thisWeek.setVisibility(View.GONE);
            thisWeekText.setVisibility(View.GONE);
        } else {
            String thisWeekStr = "";
            for (Event e: eventsThisWeek.keySet()){
                thisWeekStr = thisWeekStr.concat(e.getEventName());
                thisWeekStr = thisWeekStr.concat(" at ");
                thisWeekStr = thisWeekStr.concat(e.getLocation());
                thisWeekStr = thisWeekStr.concat("\n");
            }
            thisWeekText.setText(thisWeekStr);
        }
        if (eventsNextWeek.size() == 0) {
            nextWeek.setVisibility(View.GONE);
        } else {
            String nextWeekStr = "";
            for (Event e: eventsNextWeek.keySet()){
                nextWeekStr = nextWeekStr.concat(e.getEventName());
                nextWeekStr = nextWeekStr.concat(" at ");
                nextWeekStr = nextWeekStr.concat(e.getLocation());
                nextWeekStr = nextWeekStr.concat("\n");
            }
            nextWeekText.setText(nextWeekStr);
        }
        if (allEvents.size()== 0) {
            nextWeekText.setVisibility(View.GONE);
        } else {
            String allStr = "";
            for (Event e: allEvents.keySet()){
                allStr = allStr.concat(e.getEventName());
                allStr = allStr.concat(" at ");
                allStr = allStr.concat(e.getLocation());
                allStr = allStr.concat("\n");
            }
            nextWeekText.setText(allStr);
        }
    }

    private void addToEventSet(Event e) {
        Set<Calendar> time = new HashSet<>();
        Calendar start = Utility.digitToCalendar(e.getDayOfYear(), e.getYear(), e.getTimeDayStart());
        Calendar end = Utility.digitToCalendar(e.getDayOfYear(), e.getYear(), e.getTimeDayEnd());
        time.add(start);
        time.add(end);
        String str = "";
        str = str.concat(e.getEventName());
        str = str.concat(" at ");
        str = str.concat(e.getLocation());
        str = str.concat("\n");
        if (Utility.isToday(end)) {
            today.setVisibility(View.VISIBLE);
            todayText.setVisibility(View.VISIBLE);
            eventsToday.put(e, time);
            str = str.concat(todayText.getText().toString());
            todayText.setText(str);
        } else if (Utility.isLaterThisWeek(end)) {
            thisWeek.setVisibility(View.VISIBLE);
            thisWeekText.setVisibility(View.VISIBLE);
            eventsThisWeek.put(e, time);
            str = str.concat(thisWeekText.getText().toString());
            thisWeekText.setText(str);
        }
        if (Utility.isNextWeek(end)) {
            nextWeekText.setVisibility(View.VISIBLE);
            nextWeek.setVisibility(View.VISIBLE);
            eventsNextWeek.put(e, time);
            str = str.concat(nextWeekText.getText().toString());
            nextWeekText.setText(str);
        }
        allEvents.put(e, time);
        if (eventsToday.size() == 1 || eventsThisWeek.size() == 1 || eventsNextWeek.size() == 1){
            ((TextView) findViewById(R.id.upcomingEvents)).setText("Upcoming Events: ");
        }
    }

}