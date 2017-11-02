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
import com.cultivate.juniordesign.cultivate.Event;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Forrest on 10/13/2017.
 * This class provides the base functionality for all activities with the sidebar accessible
 */
public class HamburgerActivity extends AppCompatActivity {
    protected Account user;
    protected ConstraintLayout mainLayout;
    protected FirebaseHandler database = new FirebaseHandler();

    /**
     * opens the Quick Tools Sidebar
     * @param v the view which is automatically passed in by the button
     */
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

    /**
     * starts the My Events Activity
     * @param v the view which is automatically passed in by the button
     */
    public void goToMyEvents(View v) {
        Intent event = new Intent(this, MyEventsActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    /**
     * starts the My Profile Activity
     * @param v the view which is automatically passed in by the button
     */
    public void goToMyProfile(View v){
        Intent profile = new Intent(this, MyProfileActivity.class);
        profile.putExtra("curUser", user);
        startActivity(profile);
    }

    /**
     * /**
     * starts the My Groups Activity
     * @param v the view which is automatically passed in by the button
     */
    public void goToMyGroups(View v) {
        Intent groups = new Intent(this, MyGroupsActivity.class);
        groups.putExtra("curUser", user);
        startActivity(groups);
    }

    /**
     * Logs the user back out and puts them at the login screen
     * @param v the view which is automatically passed in by the button
     */
    public void goToLogout(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    /**
     * Starts the Main Activty (home screen)
     * @param v the view which is automatically passed in by the button
     */
    public void goToHome(View v) {
        Intent event = new Intent(this, MainActivity.class);
        event.putExtra("curUser", user);
        startActivity(event);
    }

    /**
     * Updates the given user in the database
     * @param curUser the updated user to change in the database
     */
    public void updateUser(Account curUser) {
        database.pushAccountChange(curUser);
    }

    /**
     * Updates the given Group in the database
     * @param curGroup the updated group to change in the database
     */
    public void updateGroup(Group curGroup) {
        database.pushGroupChange(curGroup);
    }

    /**
     * Updates the given Event in the database
     * @param curEvent the updated event to change in the database
     */
    public void updateEvent(Event curEvent) {
        database.pushEventChange(curEvent);
    }

    /**
     * Pops up a toast for any currently non-implemented feature
     * @param v the view which is automatically passed in by the button
     */
    public void notImplemented(View v) {
        Toast.makeText(this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
    }

}
