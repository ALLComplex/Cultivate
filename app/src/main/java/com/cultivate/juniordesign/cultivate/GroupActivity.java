package com.cultivate.juniordesign.cultivate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Paul on 9/10/2017.
 */

public class GroupActivity extends AppCompatActivity {
    private int uid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = getIntent().getIntExtra("uid", 0);
        setContentView(R.layout.group_stub);
    }

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        startActivity(event);
    }
}
