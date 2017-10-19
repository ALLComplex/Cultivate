package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.R;

/**
 * Created by Forrest on 10/15/2017.
 */

public class GroupListActivity extends HamburgerActivity {
    private Account user = null;
    private boolean isAdmin = false;
    int output = View.VISIBLE;
    TextView adminTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_my_groups);
        isAdmin = checkIfAdmin();
        //TextView adminTextView  = (TextView)findViewById(R.id.adminTextView);
        //adminTextView.setVisibility(output);
    }

    /**
     * Queries the database to determine if the user is an admin for this group. If so, return true
     * return boolean whether or not the user is an admin
     */
    private boolean checkIfAdmin() {
        return true;

    }

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void openHamburgerBar(View v) {
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.group_list_layout);
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
        Intent group = new Intent(this, GroupProfileActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    public void goToJoinGroup(View v) {
        Intent group = new Intent(this, JoinGroupActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    public void goToCreateGroup1(View v) {
        Intent group = new Intent(this, CreateGroupActivity.class);
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
        Toast.makeText(GroupListActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}