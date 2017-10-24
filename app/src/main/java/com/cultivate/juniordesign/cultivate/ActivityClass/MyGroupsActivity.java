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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_my_groups);
        mainLayout = (ConstraintLayout) findViewById(R.id.my_groups_layout);
        Map<String, Boolean> groups = user.getMemberGroups();
        Button lastButton = null;
        Button curButton;
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
        }
        TextView groupTextView = (TextView) findViewById(R.id.textView);
        if (lastButton == null) {
            groupTextView.setText("You are not a part of any groups");
        } else {
            groupTextView.setText("");
            groupTextView.setVisibility(View.INVISIBLE);
        }


        //TextView adminTextView  = (TextView)findViewById(R.id.adminTextView);
        //adminTextView.setVisibility(output);
    }

    /**
     * Starts the create group activity where the user may create a new group
     * @param v the view which is automatically passed in by the button
     */
    public void goToCreateGroup(View v) {
        Intent group = new Intent(this, CreateGroupActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    /**
     * Starts the create
     * @param v the view which is automatically passed in by the button
     */
    public void goToJoinGroup(View v) {
        Intent group = new Intent(this, JoinGroupActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

}