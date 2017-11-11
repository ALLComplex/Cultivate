package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.cultivate.juniordesign.cultivate.Utility;

import java.util.Calendar;

/**
 * Created by Forrest on 10/21/2017.
 */

public class CreateEventActivity extends HamburgerActivity {
    private Group curGroup = null;
    private EditText editTextName;
    private EditText editTextLocation;
    private Spinner month;
    private Spinner day;
    private Spinner year;
    private Spinner startHour;
    private Spinner endHour;
    private Spinner startMinute;
    private Spinner endMinute;
    private Spinner startAM;
    private Spinner endAM;
    private CheckBox allDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        user = getIntent().getParcelableExtra("curUser");
        mainLayout = (ConstraintLayout) findViewById(R.id.create_event_layout);
        curGroup = getIntent().getParcelableExtra("curGroup");
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        setSpinnerAdapter(R.id.months, R.array.months);
        setSpinnerAdapter(R.id.days, R.array.days);
        setSpinnerAdapter(R.id.years, R.array.years);
        setSpinnerAdapter(R.id.startHour, R.array.hours);
        setSpinnerAdapter(R.id.endHour, R.array.hours);
        setSpinnerAdapter(R.id.endMinute, R.array.minutes);
        setSpinnerAdapter(R.id.startMinute, R.array.minutes);
        setSpinnerAdapter(R.id.startAM, R.array.amPm);
        setSpinnerAdapter(R.id.endAM, R.array.amPm);
        month = (Spinner) findViewById(R.id.months);
        day = (Spinner) findViewById(R.id.days);
        year = (Spinner) findViewById(R.id.years);
        startHour = (Spinner) findViewById(R.id.startHour);
        endHour = (Spinner) findViewById(R.id.endHour);
        startMinute = (Spinner) findViewById(R.id.startMinute);
        endMinute = (Spinner) findViewById(R.id.endMinute);
        startAM = (Spinner) findViewById(R.id.startAM);
        endAM = (Spinner) findViewById(R.id.endAM);
        allDay = (CheckBox) findViewById(R.id.checkBox);
    }

    /**
     *
     * @param v
     */
    public void createEvent(View v) {
        //TODO: check if group name exists.
        String name = editTextName.getText().toString();
        String location = editTextLocation.getText().toString();

        //parse selections to get start and end dates
        String sMonth = month.getSelectedItem().toString();
        int sDay = Integer.parseInt(day.getSelectedItem().toString());
        int sYear = Integer.parseInt(year.getSelectedItem().toString());
        Event event;
        if (allDay.isChecked()) {
            Calendar startTime = Utility.userInputToCalendar(sMonth, sDay, sYear, 0, 0, null);
            int[] digits = Utility.calendarToDigit(startTime);
            int dayOfYear = digits[0];
            int year = digits[1];
            event = new Event(name, curGroup.getGroupName(), location, dayOfYear, year, 0, 0);
        } else {
            int sHour = Integer.parseInt(startHour.getSelectedItem().toString());
            int sMinute = Integer.parseInt(startMinute.getSelectedItem().toString());
            int eHour = Integer.parseInt(endHour.getSelectedItem().toString());
            int eMinute = Integer.parseInt(endMinute.getSelectedItem().toString());
            String eAM = endAM.getSelectedItem().toString();
            String sAM = startAM.getSelectedItem().toString();
            Calendar startTime = Utility.userInputToCalendar(sMonth, sDay, sYear, sHour, sMinute, sAM);
            int[] digits = Utility.calendarToDigit(startTime);
            int dayOfYear = digits[0];
            int year = digits[1];
            int timeDayStart = digits[2];
            Calendar endTime = Utility.userInputToCalendar(sMonth, sDay, sYear, eHour, eMinute, eAM);
            digits = Utility.calendarToDigit(endTime);
            int timeDayEnd = digits[2];

            event = new Event(name, curGroup.getGroupName(), location, dayOfYear, year, timeDayStart, timeDayEnd);
        }
        user.attendEvent(event);
        curGroup.addEvent(event);
        updateUser(user);
        updateEvent(event);
        updateGroup(curGroup);
        goToEventActivity(v, event);
    }

    /**
     *
     * @param v
     * @param e
     */
    public void goToEventActivity(View v, Event e) {
        Intent event = new Intent(this, EventActivity.class);
        event.putExtra("curUser", user);
        event.putExtra("curEvent", e);
        startActivity(event);
    }

    /**
     *
     * @param v
     */
    public void allDayBoxClicked(View v){
        if (((CheckBox) v).isChecked()) {
            setTimeGone();
        } else {
            setTimeVisible();
        }
    }

    /**
     *
     */
    public void setTimeGone(){
        ((TextView) findViewById(R.id.textStart)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.textEnd)).setVisibility(View.GONE);
        ((TableLayout)findViewById(R.id.tableEndTime)).setVisibility(View.GONE);
        ((TableLayout)findViewById(R.id.tableStartTime)).setVisibility(View.GONE);
    }

    /**
     *
     */
    public void setTimeVisible(){
        ((TextView) findViewById(R.id.textStart)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.textEnd)).setVisibility(View.VISIBLE);
        ((TableLayout)findViewById(R.id.tableEndTime)).setVisibility(View.VISIBLE);
        ((TableLayout)findViewById(R.id.tableStartTime)).setVisibility(View.VISIBLE);
    }

    /**
     *
     * @param spinnerView
     * @param resourceView
     */
    private void setSpinnerAdapter(int spinnerView, int resourceView) {
        Spinner spinner = (Spinner) findViewById(spinnerView);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(resourceView));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
