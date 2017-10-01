package com.cultivate.juniordesign.cultivate;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Paul on 9/10/2017.
 */

public class GroupActivity extends AppCompatActivity {   

    private Account user = null;
    private boolean isAdmin = false;
    int output = View.VISIBLE;
    TextView adminTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_group);
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

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void openHamburgerBar(Account user) {
        // get a reference to the already created main layout
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.group_layout);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.hamburgerbar_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.TOP | Gravity.LEFT, 0, 0);
    }

    public void goToStub(Account user) {
        Intent stub = new Intent(this, StubActivity.class);
        stub.putExtra("curUser", user);
        startActivity(stub);
    }

    public void goToEvent(Account user) {
        Intent event = new Intent(this, EventActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void goToProfile(Account user) {
        Intent profile = new Intent(this, ProfileActivity.class);
        profile.putExtra("curUser", user);
        startActivity(profile);
    }

    public void goToGroup(Account user) {
        Intent group = new Intent(this, GroupActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    public void goToCreateGroup(View v) {
        Intent group = new Intent(this, CreateGroupActivity.class);
        group.putExtra("uid", uid);
        startActivity(group);
    }

    public void goToJoinGroup(View v) {
        Intent group = new Intent(this, JoinGroupActivity.class);
        group.putExtra("uid", uid);
        startActivity(group);
    }

    /*
    goes to the login screen; clears user data
     */
    public void goToLogout(Account user) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void notImplemented(Account user) {
        Toast.makeText(GroupActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}