package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.R;

/**
 * Created by Forrest on 10/13/2017.
 */

public class HamburgerActivity extends AppCompatActivity {
    protected Account user;
    protected ConstraintLayout mainLayout;

    public void openHamburgerBar(View v) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.hamburgerbar_popup, null);
        TextView aView = (TextView) popupView.findViewById(R.id.userName);
        aView.setText(user.getName());

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable); //null for GroupProfileActivityPage.

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.TOP | Gravity.LEFT, 0, 0);
    }

    public void goToMyEvents(View v) {
        Intent event = new Intent(this, MyEventsActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void goToMyProfile(View v){
        Intent profile = new Intent(this, ProfileActivity.class);
        profile.putExtra("curUser", user);
        startActivity(profile);
    }

    public void goToMyGroups(View v) {
        Intent groups = new Intent(this, MyGroupsActivity.class);
        groups.putExtra("curUser", user);
        startActivity(groups);
    }

    public void goToLogout(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    public void notImplemented(View v) {
        Toast.makeText(this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

}
