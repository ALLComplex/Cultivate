package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.R;

/**
 * Created by Forrest on 10/13/2017.
 */

public class GroupProfileActivity extends HamburgerActivity {
    private Account user = null;
    private boolean isAdmin = false;
    boolean member = false;
    int output = View.VISIBLE;
    TextView adminTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_my_groups);
        isAdmin = checkIfAdmin();
        TextView adminTextView  = (TextView)findViewById(R.id.adminTextView);
        adminTextView.setVisibility(output);
    }

    private boolean checkIfAdmin() {
        //checkIfAdmin(user, group)
        return false;
    }

    private boolean checkIfMember() {
        //TODO check if user is a part of group
        return true;
    }

    public void goToJoinLeaveGroup(View v) {
        if (member) {
            goToLeaveGroup(v);
        } else {
            goToJoinGroup(v);
        }
        Intent group = new Intent(this, JoinGroupActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }
    //TODO throw up a toast for a "are you sure?"\
    //upd
    private void goToLeaveGroup(View v) {
        //removeGroupFromUser()
        //removeUserFromGroup()
        //removeUserFromAccount()
        Intent group = new Intent(this, GroupProfileActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    private void goToJoinGroup(View v) {
        //addGroupToUser()
        //addGroupToAccount()
        //addUsertoGroup()
        Intent group = new Intent(this, GroupProfileActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    private void addGroupToUser(View v) {

    }

    public void notImplemented(View v) {
        Toast.makeText(GroupProfileActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
