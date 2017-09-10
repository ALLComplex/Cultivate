package com.cultivate.juniordesign.cultivate;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import static android.R.attr.duration;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Snackbar mySnackbar = Snackbar.make((findViewById(R.id.home_layout)), "Welcome to the Homepage", 1000);
        mySnackbar.show();
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


}