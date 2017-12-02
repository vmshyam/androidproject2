package com.example.ihp.mobileapplicationprog3210;

/**
 * This Java file is related to 'activity_admin_user_database.xml'
 * The purpose of this file is to allow the Admin of the app to
 *      view all the user account that are created within the app
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminUserDatabaseActivity extends AppCompatActivity {

    private Button btnJvAdminSettings;

    private Button btnJvUserDB;

    private Button btnJvPhotoDB;

    TextView tvSignOutClick;

    private static final String TAG = "";

    DatabaseHelper mDatabaseHelper;

    private ListView lvUserDetailsView;

    ArrayList<UserDetailData> listOfUserDetailsData =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_database);

        mDatabaseHelper = new DatabaseHelper(this);

        btnJvAdminSettings = (Button) findViewById(R.id.btnNavAdminSettings);

        btnJvUserDB = (Button) findViewById(R.id.btnNavUserDB);

        btnJvPhotoDB = (Button) findViewById(R.id.btnNavPhotoDB);

        lvUserDetailsView = (ListView) findViewById(R.id.lvShowUserDB);

        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);

        //Method used to populate the List view of this related activity to show user account created within the app
        PopulateListView();

        //Required to use custom adapter view to show Admin a custom list view experience
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_user_display_layout, listOfUserDetailsData);
        lvUserDetailsView.setAdapter(adapter);

        //Navigation button to Home Admin Setting activity
        btnJvAdminSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminUserDatabaseActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        //Navigation button to show all user photos added to the account
        btnJvPhotoDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminUserDatabaseActivity.this, AdminPhotoDatabaseActivity.class);
                startActivity(intent);
            }
        });

        //Button to sign out of the app
        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(AdminUserDatabaseActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });
    }

    /** Method used to populate the List view of this related activity to show user account created within the app  **/
    private void PopulateListView(){

        Cursor allUserDetailsContent = mDatabaseHelper.getRetrieveUserData();

        while (allUserDetailsContent.moveToNext()){

            listOfUserDetailsData.add(new UserDetailData(allUserDetailsContent.getString(1),
                    allUserDetailsContent.getString(2),
                    allUserDetailsContent.getString(3)));

        }
    }

    /** Method used to allow the use of custom layout activity to display custom formatted views **/
    public class CustomAdapter extends BaseAdapter
    {

        private Context context;

        private int layout;

        ArrayList<UserDetailData> dataList;

        public CustomAdapter(Context context, int layout, ArrayList<UserDetailData> dataList) {

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

            TextView hFullName;

            TextView hUsername;

            TextView hEmail;

        }

        @Override
        public View getView(int position, View view, ViewGroup parent){

            View row = view;

            ViewHolder holder;

            if(row == null){

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

                row = inflater.inflate(layout, null);

                holder = new CustomAdapter.ViewHolder();

                holder.hFullName = row.findViewById(R.id.tvUsername);

                holder.hUsername = row.findViewById(R.id.tvDateStamp);

                holder.hEmail = row.findViewById(R.id.tvEmail);

                row.setTag(holder);

            } else {

                holder = (ViewHolder) row.getTag();
            }

            final UserDetailData userDetailsList = dataList.get(position);

            holder.hFullName.setText(userDetailsList.getFullName());

            holder.hUsername.setText(userDetailsList.getUsername());

            holder.hEmail.setText(userDetailsList.getEmail());

            return row;
        }
    }
}
