package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.R;

/**
 * Created by Forrest on 10/13/2017.
 */

abstract class HamburgerActivity extends AppCompatActivity {

    public void openHamburgerBar(ConstraintLayout mainLayout, View v, Account user) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.hamburgerbar_popup, null);
        TextView aView = (TextView) popupView.findViewById(R.id.userName);
        aView.setText(user.getName());

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable); //null for GroupProfileActivityPage.

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.TOP | Gravity.LEFT, 0, 0);
    }
}
