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
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.GetDataListener;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

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
        String groupS = "My Groups: ";
        Button lastButton = null;
        Button curButton = null;
        LinearLayout buttonLayout = (LinearLayout) findViewById(R.id.ButtonLayout);
        for (String group: groups.keySet()) {
            curButton = new Button(this);
            curButton.setId(group.hashCode());
            curButton.setText(group);
            curButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //find group by
                    String groupText = ((Button) v).getText().toString();
                    FirebaseHandler db = new FirebaseHandler();
                    db.getGroup(groupText, new GetDataListener(){
                        @Override
                        public void onStart() {
                            Log.d("STARTED", "Started");
                        }

                        @Override
                        public void onSuccess(DataSnapshot data) {
                            Group g = data.getValue(Group.class);
                            if (g != null) {
                                Log.d("ASSIGN TEMP VALUE", g.getLocation());
                                Intent groupI = new Intent(MyGroupsActivity.this, GroupProfileActivity.class);
                                groupI.putExtra("curGroup", g);
                                groupI.putExtra("curUser", user);
                                startActivity(groupI);
                            } else {
                                Toast.makeText(MyGroupsActivity.this, "This  group does not exist!", Toast.LENGTH_SHORT).show();
                                Log.d("ASSIGN TEMP VALUE", "Failure");
                            }

                        }

                        @Override
                        public void onFailed(DatabaseError databaseError) {
                            Log.d("FAILURE", "fail");
                        }
                    });
                }
            });
            buttonLayout.addView(curButton);
            lastButton = curButton;
            curButton = null;
        }
        if (lastButton == null) {
            groupS = "You are not a part of any groups";
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