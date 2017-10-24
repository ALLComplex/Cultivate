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
    SearchView mSearchView = null;
    Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.join_group_layout);
        mainLayout = (ConstraintLayout) findViewById(R.id.join_group_layout);
        mSearchView = (SearchView) findViewById(R.id.search_bar);
    }

    public void goToJoinGroup(final View v) {
        String s = mSearchView.getQuery().toString();
        if (user.getMemberGroups().containsKey(s)) {
            //toast that user is already a part of the group!
        } else {
            database.getGroup(s, new GetDataListener() {
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
                        updateUser(user);
                        updateGroup(group);
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

    public void goToGroupProfilePage(View v) {
        Intent groupProfile = new Intent(this, MyGroupsActivity.class);
        groupProfile.putExtra("curUser", user);
        groupProfile.putExtra("curGroup", group);
        startActivity(groupProfile);
    }

}
