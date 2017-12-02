package com.example.ihp.mobileapplicationprog3210;

/**
 * This Java file is related to 'activity_admin_account_log.xml'
 * The purpose of this file is to allow the Admin of the app to
 *      view when an user has loggedIn and to monitor user activity
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminAccountLogsActivity extends AppCompatActivity {

    private Button btnJvAdminSettings, btnJvUserDB, btnJvPhotoDB;

    TextView tvSignOutClick;

    private static final String TAG = "";

    DatabaseHelper mDatabaseHelper;

    private ListView mAccountLogListView;

    ArrayList<AccountUserLogData> listOfAccountLoginDetailsData =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account_logs);

        mDatabaseHelper = new DatabaseHelper(this);

        btnJvAdminSettings = (Button) findViewById(R.id.btnALNavAdminSettings);

        btnJvUserDB = (Button) findViewById(R.id.btnALNavUserDB);

        btnJvPhotoDB = (Button) findViewById(R.id.btnALNavPhotoDB);

        mAccountLogListView = (ListView) findViewById(R.id.lvShowAccountLogDB);

        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);

        //Method used to populate the List view of this related activity to show user account activity information
        PopulateListView();

        //Required to use custom adapter view to show Admin a custom list view experience
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_account_activity_display_layout, listOfAccountLoginDetailsData);
        mAccountLogListView.setAdapter(adapter);

        //Navigation button to Home Admin Setting activity
        btnJvAdminSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminAccountLogsActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        //Navigation button to show all active users of the app activity
        btnJvUserDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminAccountLogsActivity.this, AdminUserDatabaseActivity.class);
                startActivity(intent);
            }
        });

        //Navigation button to show all the photos added by all users of the app
        btnJvPhotoDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminAccountLogsActivity.this, AdminPhotoDatabaseActivity.class);
                startActivity(intent);
            }
        });

        //Button to sign out of the app
        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(AdminAccountLogsActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });
    }

    /** Method to populate the List view of this related activity to show user account activity information  **/
    private void PopulateListView(){

        Log.d(TAG, "populateListView: AccountLogsAct");

        Cursor allAccountUserLoginContent = mDatabaseHelper.getRetrieveAccountLogData();

        if (allAccountUserLoginContent.getCount() != 0){

            while (allAccountUserLoginContent.moveToNext()){

                listOfAccountLoginDetailsData.add(new AccountUserLogData(allAccountUserLoginContent.getString(2),
                        allAccountUserLoginContent.getString(3)));

            }
        }
        else {

            listOfAccountLoginDetailsData.add(new AccountUserLogData("No Current Entry",
                    "No Current Entry"));

        }

    }

    /** Method used to allow the use of custom layout activity to display custom formatted views **/
    public class CustomAdapter extends BaseAdapter{

        private Context context;

        private int layout;

        ArrayList<AccountUserLogData> dataList;

        public CustomAdapter(Context context, int layout, ArrayList<AccountUserLogData> dataList) {

            this.context = context;

            this.layout = layout;

            this.dataList = dataList;
        }

        @Override
        public int getCount(){

            return dataList.size();
        }

        @Override
        public Object getItem(int position){

            return dataList.get(position);
        }

        @Override
        public long getItemId(int position){

            return position;
        }

        private class ViewHolder{

            TextView hUsername;

            TextView hTimestamp;
        }


        @Override
        public View getView(int position, View view, ViewGroup parent){

            View row = view;

            ViewHolder holder;

            if(row == null){

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

                row = inflater.inflate(layout, null);

                holder = new CustomAdapter.ViewHolder();

                holder.hUsername = row.findViewById(R.id.tvUsername);

                holder.hTimestamp = row.findViewById(R.id.tvDateStamp);

                row.setTag(holder);

            } else {

                holder = (ViewHolder) row.getTag();

            }

            final AccountUserLogData accountUserLogData = dataList.get(position);

            holder.hUsername.setText(accountUserLogData.getUsername());

            holder.hTimestamp.setText(accountUserLogData.getTimestamp());

            return row;

        }
    }
}
