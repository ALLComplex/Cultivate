package com.cultivate.juniordesign.cultivate.ActivityClass;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

/**
 * Created by Paul on 9/10/2017.
 */

public class ProfileActivity extends HamburgerActivity {
    private Account user = null;
    EditText profileName = null;
    TextView profileEmail = null;
    TextView profilePhone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        setContentView(R.layout.activity_my_profile);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        profileName = (EditText) findViewById(R.id.my_profile_name);

        profileEmail = (TextView) findViewById(R.id.my_profile_email);
        profilePhone = (TextView) findViewById(R.id.my_profile_phone);
        String email = user.getEmail();
        email = email.replace('_', '.');

        profilePhone.setText(user.getPhone());
        profileName.setText(user.getName());
        profileEmail.setText(email);
    }

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void openHamburgerBar(View v) {
        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.my_profile_layout);
        super.openHamburgerBar(mainLayout, v, user);
    }

    public void goToStub(View v) {
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

    public void updateUser(View v) {
        String newName =  profileName.getText().toString();
        String newPhone = profilePhone.getText().toString();
        //String newEmail = profileEmail.getText().toString();
        user.setName(newName);
        user.setPhone(newPhone);
        //user.setEmail(newEmail);
        FirebaseHandler db = new FirebaseHandler();
        db.pushAccountChange(user);
        refreshPage(v);
    }

    public void refreshPage(View v) {
        Intent group = new Intent(this, ProfileActivity.class);
        group.putExtra("curUser", user);
        startActivity(group);
    }

    /*
    goes to the login screen; clears user data
     */
    public void goToLogout(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void notImplemented(View v) {
        Toast.makeText(ProfileActivity.this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
