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

import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.GetDataListener;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.Map;

/**
 * Created by Forrest on 11/10/2017.
 */

public class AttendanceActivity extends HamburgerActivity {
    private Event event;
    LinearLayout buttonLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        event = getIntent().getParcelableExtra("curEvent");
        setContentView(R.layout.activity_attendance);
        mainLayout = (ConstraintLayout) findViewById(R.id.my_groups_layout);
        Map<String, Boolean> attenders = event.getPeopleAttending();
        TextView lastButton = null;
        TextView curButton;
        buttonLayout = (LinearLayout) findViewById(R.id.ButtonLayout);
        for (String users: attenders.keySet()) {
            curButton = new TextView(this);
            curButton.setId(users.hashCode());
            curButton.setText(users);
            buttonLayout.addView(curButton);
            lastButton = curButton;
        }
        //TODO change to displaying names not emails
        TextView groupTextView = (TextView) findViewById(R.id.textView);
        if (lastButton != null) {
            groupTextView.setVisibility(View.GONE);
        }
    }

}
