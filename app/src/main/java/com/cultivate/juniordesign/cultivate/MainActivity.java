package com.cultivate.juniordesign.cultivate;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

import static android.R.attr.duration;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Snackbar mySnackbar = Snackbar.make((findViewById(R.id.home_layout)), "Welcome to the Homepage", 1000);
        mySnackbar.show();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Snackbar email = Snackbar.make((findViewById(R.id.home_layout)),"e-mail: " + user.getEmail(), 10000);
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
        popupWindow.showAtLocation(mainLayout, Gravity.TOP|Gravity.LEFT, 0, 0);
    }

    public void goToStub(View v){
//        Snackbar mySnackbar = Snackbar.make((findViewById(R.id.home_layout)), "Can't go to the Stub Page", 10000);
//        mySnackbar.show();
        Intent stub = new Intent(this, StubActivity.class);
        startActivity(stub);
    }

    public void goToEvent(View v) {
        Intent event = new Intent(this, EventActivity.class);
        startActivity(event);
    }

    public void goToProfile(View v) {
        Intent profile = new Intent(this, ProfileActivity.class);
        startActivity(profile);
    }

    public void goToGroup(View v) {
        Intent group = new Intent(this, GroupActivity.class);
        startActivity(group);
    }

    public void notImplemented(View v) {
        Toast.makeText(MainActivity.this, "This feature is not yet implemented",Toast.LENGTH_SHORT).show();
    }


}