package com.cultivate.juniordesign.cultivate.ActivityClass;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class MainActivity extends HamburgerActivity {
    private Account user = null;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getIntent().getParcelableExtra("curUser");
        Snackbar mySnackbar = Snackbar.make((findViewById(R.id.home_layout)), "Welcome to the Homepage", 1000);
        mySnackbar.show();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser authUser = mAuth.getCurrentUser();
        Snackbar email = Snackbar.make((findViewById(R.id.home_layout)), "e-mail: " + user.getEmail(), 10000);
        email.show();
    }

    public void openHamburgerBar(View v) {
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.home_layout);
        super.openHamburgerBar(mainLayout, v, user);
    }

    public void goToStub(View v) {
//        Snackbar mySnackbar = Snackbar.make((findViewById(R.id.home_layout)), "Can't go to the Stub Page", 10000);
//        mySnackbar.show();
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