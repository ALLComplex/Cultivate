package com.cultivate.juniordesign.cultivate.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cultivate.juniordesign.cultivate.Account;
import com.cultivate.juniordesign.cultivate.FirebaseHandler;
import com.cultivate.juniordesign.cultivate.GetDataListener;
import com.cultivate.juniordesign.cultivate.Group;
import com.cultivate.juniordesign.cultivate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cathy on 10/20/2017.
 */

public class MembersListActivity extends HamburgerActivity {
    private Group thisGroup = null;
    FirebaseHandler db;
    TextView title;
    TextView list;
    Set<Account> members = new HashSet<>();
    String listS;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getIntent().getParcelableExtra("curUser");
        thisGroup = getIntent().getParcelableExtra("curGroup");
        setContentView(R.layout.activity_member_list);
        mainLayout = (ConstraintLayout) findViewById(R.id.member_list_layout);
        title = (TextView) findViewById(R.id.title);
        title.setText(thisGroup.getGroupName().concat("'s Member List"));
        list = (TextView) findViewById(R.id.list);
        listS = "";
        for (String str:  thisGroup.getGroupMembers().keySet()) {
            FirebaseHandler db = new FirebaseHandler();
            db.getAccount(str, new GetDataListener(){
                @Override
                public void onStart() {
                    Log.d("STARTED", "Started");
                }

                @Override
                public void onSuccess(DataSnapshot data) {
                    Account a = (Account) data.getValue(Account.class);
                    if (a != null) {
                        Log.d("ASSIGN TEMP VALUE", a.getName());
                        addToMemberList(a);
                    } else {
                        Toast.makeText(MembersListActivity.this, "This member does not exist!", Toast.LENGTH_SHORT).show();
                        Log.d("ASSIGN TEMP VALUE", "Failure");
                    }

                }

                @Override
                public void onFailed(DatabaseError databaseError) {
                    Log.d("FAILURE", "fail");
                }
            });
        }
        if (members.size() == 0) {
            list.setText("No members found.");
        }
    }

    public void addToMemberList(Account a) {
        members.add(a);
        if (!listS.equals("")) {
            listS = listS.concat("\n");
        }
        listS = listS.concat(a.getName());
        listS = listS.concat(":   ");
        listS = listS.concat(a.getEmail().replace('_', '.'));
        list.setText(listS);
    }
}
