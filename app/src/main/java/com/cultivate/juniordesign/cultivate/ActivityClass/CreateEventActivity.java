package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;

/**
 * Created by Forrest on 10/21/2017.
 */

public class CreateEventActivity extends HamburgerActivity {
    private Group aGroup = null;
    private EditText editTextName;
    private EditText editTextLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        user = getIntent().getParcelableExtra("curUser");
        mainLayout = (ConstraintLayout) findViewById(R.id.create_group_layout);
        aGroup = getIntent().getParcelableExtra("curGroup");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
    }

    public void createEvent(View v) {
        //TODO: check if group name exists.
        String name = editTextName.getText().toString();
        String location = editTextLocation.getText().toString();
        Event event = new Event(name, aGroup.getGroupName(), location);
        user.attendEvent(event);
        aGroup.addEvent(event);
        FirebaseHandler db = new FirebaseHandler();
        db.pushAccountChange(user);
        db.pushEventChange(event);
        db.pushGroupChange(aGroup);
        goToEventActivity(v, event);
       // goToGroupProfile(v, aGroup);
    }

    private void goToGroupProfile(View v, Group g) {
        Intent group = new Intent(this, GroupProfileActivity.class);
        group.putExtra("curGroup", g);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    private void goToEventActivity(View v, Event e) {
        Intent group = new Intent(this, EventActivity.class);
        group.putExtra("curUser", user);
        group.putExtra("curEvent", e);
        startActivity(group);
    }

}
