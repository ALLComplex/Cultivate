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
    private boolean isAdmin = false;
    int output = View.VISIBLE;
    TextView adminTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_my_groups);
        mainLayout = (ConstraintLayout) findViewById(R.id.my_groups_layout);
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

    public void goToJoinGroup(View v) {
        Intent group = new Intent(this, JoinGroupActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    public void goToCreateGroup(View v) {
        Intent group = new Intent(this, CreateGroupActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

}