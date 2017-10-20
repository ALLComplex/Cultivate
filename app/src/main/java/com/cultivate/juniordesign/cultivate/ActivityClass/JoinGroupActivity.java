package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.GetDataListener;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by Paul on 9/10/2017.
 */

public class JoinGroupActivity extends HamburgerActivity {
    private Account user = null;
    SearchView mSearchView = null;
    Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.join_group_layout);
        mSearchView = (SearchView) findViewById(R.id.search_bar);
    }

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void openHamburgerBar(View v) {
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.join_group_layout);
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

    public void goToJoinGroup(final View v) {
        final FirebaseHandler db = new FirebaseHandler();
        String s = mSearchView.getQuery().toString();
        if (user.getMemberGroups().containsKey(s)) {
            //toast that user is already a part of the group!
        } else {
            db.getGroup(s, new GetDataListener() {
                @Override
                public void onStart() {
                    Log.d("STARTED", "Started");
                }

                @Override
                public void onSuccess(DataSnapshot data) {
                    group = data.getValue(Group.class);
                    if (group != null) {
                        Log.d("ASSIGN TEMP VALUE", group.getLocation());
                        user.becomeMember(group);
                        group.addMember(user);
                        db.pushAccountChange(user);
                        db.pushGroupChange(group);
                        goToGroupProfilePage(v);
                    } else {
                        Toast.makeText(JoinGroupActivity.this, "This  group does not exist!", Toast.LENGTH_SHORT).show();
                        Log.d("ASSIGN TEMP VALUE", "Failure");
                    }

                    //call joinGroup
                }

                @Override
                public void onFailed(DatabaseError databaseError) {
                    Log.d("FAILURE", "fail");
                }
            });
        }
    }

    /*
    goes to the login screen; clears user data
     */
    public void goToLogout(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void goToGroupProfilePage(View v) {
        Intent groupProfile = new Intent(this, MyGroupsActivity.class);
        groupProfile.putExtra("curUser", user);
        groupProfile.putExtra("curGroup", group);
        startActivity(groupProfile);
    }

    public void notImplemented(View v) {
        Toast.makeText(JoinGroupActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
