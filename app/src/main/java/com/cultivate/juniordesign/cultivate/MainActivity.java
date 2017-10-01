package com.cultivate.juniordesign.cultivate;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private Account user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getIntent().getParcelableExtra("curUser");
        Snackbar mySnackbar = Snackbar.make((findViewById(R.id.home_layout)), "Welcome to the Homepage", 1000);
        mySnackbar.show();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Snackbar email = Snackbar.make((findViewById(R.id.home_layout)), "e-mail: " + user.getEmail(), 10000);
        email.show();
    }

    public void openHamburgerBar(View v) {
        // get a reference to the already created main layout
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.home_layout);

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
//        Snackbar mySnackbar = Snackbar.make((findViewById(R.id.home_layout)), "Can't go to the Stub Page", 10000);
//        mySnackbar.show();
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

    public void notImplemented(View v) {
        Toast.makeText(MainActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }


    /*
    goes to the login screen; clears user data
     */
    public void goToLogout(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}