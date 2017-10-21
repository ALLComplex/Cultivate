package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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
    private Group thisGroup = null;
    Button joinGroup;
    Button adminRequest;
    Button memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        thisGroup = getIntent().getParcelableExtra("curGroup");
        setContentView(R.layout.activity_group_profile);
        isAdmin = checkIfAdmin();
        member = checkIfMember();
        joinGroup = (Button) findViewById(R.id.joinLeaveButton);
        adminRequest = (Button) findViewById(R.id.adminButton);
        memberList = (Button) findViewById(R.id.memberListButton);
        if (member) {
            joinGroup.setText("Leave Group");
        }
        if (!isAdmin) {
            adminRequest.setText("Claim to be Admin");
            memberList.setVisibility(View.INVISIBLE);
        }
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(thisGroup.getGroupName());
        TextView textLocation = (TextView) findViewById(R.id.groupLocation);
        textLocation.setText(thisGroup.getLocation());
    }

    public void openHamburgerBar(View v) {
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.group_profile_layout);
        super.openHamburgerBar(mainLayout, v, user);
    }

    private boolean checkIfAdmin() {
        return thisGroup.getGroupAdmins().containsKey(user.getEmail());
    }

    private boolean checkIfMember() {
        //TODO this isn't working?
        return thisGroup.getGroupMembers().containsKey(user.getEmail());
    }

    public void goToJoinLeaveGroup(View v) {
        if (member) {
            goToLeaveGroup(v);
        } else {
            goToJoinGroup(v);
        }
    }
    //TODO throw up a toast for a "are you sure?"\
    private void goToLeaveGroup(View v) {
        if (thisGroup.getGroupAdmins().containsKey(user.getEmail())) {
            Toast.makeText(this, "Admins cannot leave a group!", Toast.LENGTH_SHORT).show();
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

            Intent group = new Intent(this, MyGroupsActivity.class);
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

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
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

    /*
    goes to the login screen; clears user data
     */
    public void goToLogout(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void goToClaimResignAdmin(View v) {
        if (!isAdmin) {
            becomeAdmin();
        } else {
            notImplemented(v);
        }
        final FirebaseHandler db = new FirebaseHandler();
        db.pushAccountChange(user);
        db.pushGroupChange(thisGroup);
    }

    public void becomeAdmin() {
        user.becomeAdmin(thisGroup);
        adminRequest.setText("Resign as admin");
        memberList.setVisibility(View.VISIBLE);
        isAdmin = true;
    }

    public void goToMemberList(View v) {
        if (checkIfAdmin()) {
            Intent member = new Intent(this, MembersListActivity.class);
            member.putExtra("curUser", user);
            member.putExtra("curGroup", thisGroup);
            startActivity(member);
        } else {
            Toast.makeText(GroupProfileActivity.this, "You are not an admin. This feature is not accessible to you!", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToCreateEvent(View v) {
        Intent member = new Intent(this, CreateEventActivity.class);
        member.putExtra("curUser", user);
        member.putExtra("curGroup", thisGroup);
        startActivity(member);
    }

    public void notImplemented(View v) {
        Toast.makeText(GroupProfileActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
