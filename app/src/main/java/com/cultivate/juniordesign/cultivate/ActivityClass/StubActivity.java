package com.cultivate.juniordesign.cultivate.ActivityClass;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.R;

public class StubActivity extends HamburgerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_stub);
        mainLayout = (ConstraintLayout) findViewById(R.id.stub_layout);
    }
}