package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;

import java.util.HashMap;

/**
 * Created by Forrest on 10/13/2017.
 */

public class GroupProfileActivity extends HamburgerActivity {
    private Account user = null;
    private boolean isAdmin = false;
    boolean member = false;
    int output = View.VISIBLE;
    private Group thisGroup = null;
    TextView adminTextView2;
    Button joinGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_profile);
        user = getIntent().getParcelableExtra("curUser");
        thisGroup = getIntent().getParcelableExtra("curGroup");
        adminTextView2  = (TextView) findViewById(R.id.adminTextView);
        adminTextView2.setVisibility(output);
        isAdmin = checkIfAdmin();
        member = checkIfMember();

        joinGroup = (Button) findViewById(R.id.joinLeaveButton);
        if (member) {
            joinGroup.setText("Leave Group");
        }
    }

    private boolean checkIfAdmin() {
        return thisGroup.getGroupAdmins().containsKey(user.getEmail());
    }

    private boolean checkIfMember() {
        return thisGroup.getGroupMembers().containsKey(user.getEmail());
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
    private void goToLeaveGroup(View v) {
        if (thisGroup.getGroupAdmins().containsKey(user.getEmail())) {
            //TOAST "Admins cannot leave a group!"
        } else {
            HashMap<String, Boolean> memberList = (HashMap) thisGroup.getGroupMembers();
            memberList.remove(user.getEmail());
            thisGroup.setGroupMembers(memberList);

            HashMap<String, Boolean> groupList = (HashMap) user.getMemberGroups();
            groupList.remove(thisGroup.getGroupName());
            user.setMemberGroups(memberList);

            final FirebaseHandler db = new FirebaseHandler();
            db.pushAccountChange(user);
            db.pushGroupChange(thisGroup);

            Intent group = new Intent(this, GroupProfileActivity.class);
            group.putExtra("curUser", user);
            startActivity(group);
        }
    }

    private void goToJoinGroup(View v) {
        thisGroup.addMember(user);
        user.becomeMember(thisGroup);

        final FirebaseHandler db = new FirebaseHandler();
        db.pushAccountChange(user);
        db.pushGroupChange(thisGroup);

        Intent group = new Intent(this, GroupProfileActivity.class);
        group.putExtra("curUser", user);
        group.putExtra("curGroup", thisGroup);
        startActivity(group);
    }


    public void notImplemented(View v) {
        Toast.makeText(GroupProfileActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
