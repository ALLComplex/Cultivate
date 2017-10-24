package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;

/**
 * Created by Paul on 9/10/2017.
 */

public class CreateGroupActivity extends HamburgerActivity {
    private EditText editTextName;
    private EditText editTextLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.create_group_layout);
        mainLayout = (ConstraintLayout) findViewById(R.id.create_group_layout);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
    }

    public void createGroup(View v) {
        //TODO: check if group name exists.
        String name = editTextName.getText().toString();
        String location = editTextLocation.getText().toString();
        Group group = new Group(name, location);
        user.becomeAdmin(group);
        user.becomeMember(group);
        FirebaseHandler db = new FirebaseHandler();
        db.pushGroupChange(group);
        goToGroupProfile(v, group);
    }

    private void goToGroupProfile(View v, Group g) {
        Intent group = new Intent(this, GroupProfileActivity.class);
        group.putExtra("curGroup", g);
        group.putExtra("curUser", user);
        startActivity(group);
    }
}