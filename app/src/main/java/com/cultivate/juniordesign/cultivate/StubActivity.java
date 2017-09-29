package com.cultivate.juniordesign.cultivate;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StubActivity extends AppCompatActivity {
    private String uid = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = getIntent().getStringExtra("uid");
        setContentView(R.layout.activity_stub);
    }
    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("uid", uid);
        startActivity(event);
    }
}