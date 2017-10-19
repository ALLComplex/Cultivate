package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;

/**
 * Created by Paul on 9/10/2017.
 */

public class CreateGroupActivity extends HamburgerActivity {
    private Account user = null;
    private EditText editTextName;
    private EditText editTextLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.create_group_layout);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
    }

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void openHamburgerBar(View v) {
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.create_group_layout);
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

    public void createGroup(View v) {
        //TODO: check if group name exists.
        String name = editTextName.getText().toString();
        String location = editTextLocation.getText().toString();
        Group group = new Group(name, location);
        group.addMember(user);
        group.addAdmin(user);
        FirebaseHandler db = new FirebaseHandler();
        db.pushGroupChange(group);
        goToGroupProfile(v, group);
    }

    private void goToGroupProfile(View v, Group g) {
        Intent group = new Intent(this, GroupProfileActivity.class);
        group.putExtra("curGroup", g);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    /*
    goes to the login screen; clears user data  
     */
    public void goToLogout(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void notImplemented(View v) {
        Toast.makeText(CreateGroupActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}