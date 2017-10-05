package com.cultivate.juniordesign.cultivate;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by cathy on 9/28/2017.
 */

public class RegistrationActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;

    //UI Refrences
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    //currently not implemented but will be needed later.
    private EditText mNameView;
    private EditText mPhoneNumView;
    private DatabaseReference mDatabase;

    private View mProgressView;
    private View mRegistrationFormView;

    public final String successCreate = "You have successfully created a new account. Now login with your new information";

    public final String failCreate = "You have failed to create a new account. Try again.";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //// TODO: 9/28/2017 set global variables to default
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mNameView = (EditText) findViewById(R.id.name);
        mPhoneNumView = (EditText) findViewById(R.id.phone);
        //populateAutoComplete();
        mPasswordView = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

        mRegistrationFormView = findViewById(R.id.registration_form);
        mProgressView = findViewById(R.id.registration_progress);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void attemptCreateAccount(View v) {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String name = mNameView.getText().toString();
        String phone = mPhoneNumView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            createAccount(email, password, phone, name, v);
        }
    }

    public void createAccount(final String email, String password, final String phone, final String name, View v) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("FIREBASE", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            showProgress(false);
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, failCreate, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Account newUser = new Account(name, email.replace('.', '_'), phone);

                            //Group newGroup = new Group("Psi Upsilon", "Atlanta, GA", newUser.getName());
                            //newUser.becomeAdmin(newGroup);
                            mDatabase.child("users").child(newUser.getEmail()).setValue(newUser);
                            //mDatabase.child("groups").child(newGroup.getGroupName()).setValue(newGroup);
                            Toast.makeText(RegistrationActivity.this, name, Toast.LENGTH_LONG).show();
                            mAuth.signOut();
                            Toast.makeText(RegistrationActivity.this, successCreate, Toast.LENGTH_SHORT).show();
                            // ...
                            launch(newUser);
                        }
                    });
        }
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegistrationFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    public void launch(Account user){
        Intent login = new Intent(this, LoginActivity.class);
        login.putExtra("curUser", user);
        startActivity(login);
    }

}
