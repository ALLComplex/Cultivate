package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.GetDataListener;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Forrest on 10/13/2017.
 */

public class GroupProfileActivity extends HamburgerActivity {
    private boolean isAdmin = false;
    boolean member = false;
    private Group thisGroup = null;
    private ArrayList<Event> events;
    Button joinGroup;
    Button adminRequest;
    Button memberList;
    Button createEvent;
    String eventListInfo;
    TextView eventInfo;
    TextView groupName;
    TextView groupLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        thisGroup = getIntent().getParcelableExtra("curGroup");
        setContentView(R.layout.activity_group_profile);
        mainLayout = (ConstraintLayout) findViewById(R.id.group_profile_layout);
        isAdmin = checkIfAdmin();
        member = checkIfMember();
        joinGroup = (Button) findViewById(R.id.joinLeaveButton);
        adminRequest = (Button) findViewById(R.id.adminButton);
        memberList = (Button) findViewById(R.id.memberListButton);
        createEvent = (Button) findViewById(R.id.createEventButton);
        if (member) {
            joinGroup.setText("Leave Group");
        }
        if (!isAdmin) {
            adminRequest.setText("Claim to be Admin");
            memberList.setVisibility(View.GONE);
            createEvent.setVisibility(View.GONE);
        }
        groupName = (TextView) findViewById(R.id.textView2);
        groupName.setText(thisGroup.getGroupName());
        groupLocation = (TextView) findViewById(R.id.groupLocation);
        groupLocation.setText(thisGroup.getLocation());
        events = new ArrayList<>();
        eventInfo = (TextView) findViewById(R.id.eventListText);
        eventListInfo = "";
        for (String event : thisGroup.getEvents().keySet()) {
            database.getEvent(event, new GetDataListener() {
                @Override
                public void onStart() {
                    Log.d("STARTED", "Started");
                }

                @Override
                public void onSuccess(DataSnapshot data) {
                    Event e = data.getValue(Event.class);
                    if (e != null) {
                        Log.d("ASSIGN TEMP VALUE", e.getLocation());
                        addEvent(e);
                    } else {
                        Toast.makeText(GroupProfileActivity.this, "This event does not exist!", Toast.LENGTH_SHORT).show();
                        Log.d("ASSIGN TEMP VALUE", "Failure");
                    }
                }

                @Override
                public void onFailed(DatabaseError databaseError) {
                    Log.d("FAILURE", "fail");
                }
            });
        }
        if (events.size() == 0) {
            eventInfo.setText("No upcoming events");
        }
    }

    public void addEvent(Event e) {
        events.add(e);
        eventListInfo = eventListInfo.concat("\nName: " + e.getEventName());
        eventListInfo = eventListInfo.concat("\nLocation: " + e.getLocation());
        eventListInfo = eventListInfo.concat("\n");
        eventInfo.setText(eventListInfo);
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
            user.setMemberGroups(groupList);

            updateUser(user);
            updateGroup(thisGroup);

            Intent group = new Intent(this, MyGroupsActivity.class);
            group.putExtra("curUser", user);
            startActivity(group);
        }
    }

    private void goToJoinGroup(View v) {
        thisGroup.addMember(user);
        user.becomeMember(thisGroup);

        updateUser(user);
        updateGroup(thisGroup);

        Intent group = new Intent(this, GroupProfileActivity.class);
        group.putExtra("curUser", user);
        group.putExtra("curGroup", thisGroup);
        startActivity(group);
    }

    public void goToClaimResignAdmin(View v) {
        if (!isAdmin) {
            becomeAdmin();
        } else {
            resignAdmin();
        }
        updateUser(user);
        updateGroup(thisGroup);
    }

    public void becomeAdmin() {
        user.becomeAdmin(thisGroup);
        adminRequest.setText("Resign as admin");
        memberList.setVisibility(View.VISIBLE);
        isAdmin = true;
    }

    public void resignAdmin(){
        if (thisGroup.getGroupAdmins().size() > 1) {
            user.resignAdmin(thisGroup);
            adminRequest.setText("Claim to be admin");
            isAdmin = false;
        } else {
            Toast.makeText(this, "Groups must have at least one admin!", Toast.LENGTH_SHORT).show();
        }
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

}
