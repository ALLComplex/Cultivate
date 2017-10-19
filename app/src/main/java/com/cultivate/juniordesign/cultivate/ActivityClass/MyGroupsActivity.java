package com.cultivate.juniordesign.cultivate.ActivityClass;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.R;

import java.util.Map;

/**
 * Created by Paul on 9/10/2017.
 */

public class MyGroupsActivity extends HamburgerActivity {

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
        Map<String, Boolean> groups = user.getMemberGroups();
        String groupS = " ";
        for (String group: groups.keySet()) {
            groupS.concat(group + ", ");
        }
        if (groupS.equals(" ")) {
            groupS = "None ";
        }
        TextView groupTextView = (TextView) findViewById(R.id.textView);
        groupTextView.setText(groupS);
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
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.my_groups_layout);
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

    public void goToCreateGroup(View v) {
        Intent group = new Intent(this, CreateGroupActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    public void goToJoinGroup(View v) {
        Intent group = new Intent(this, JoinGroupActivity.class);
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
        Toast.makeText(MyGroupsActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}