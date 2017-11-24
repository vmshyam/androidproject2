package com.example.ihp.mobileapplicationprog3210;

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

        PopulateListView();
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_account_activity_display_layout, listOfAccountLoginDetailsData);
        mAccountLogListView.setAdapter(adapter);


        btnJvAdminSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminAccountLogsActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        btnJvUserDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminAccountLogsActivity.this, AdminUserDatabaseActivity.class);
                startActivity(intent);
            }
        });

        btnJvPhotoDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminAccountLogsActivity.this, AdminPhotoDatabaseActivity.class);
                startActivity(intent);
            }
        });

        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(AdminAccountLogsActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });
    }


/*    private void PopulateListView() {
        Log.d(TAG, "populateListView: AccountLogsAct");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getRetrieveAccountLogData();

        ArrayList<String> listData = new ArrayList<>();
        if (data.getCount() != 0) {
            while (data.moveToNext()){
                //get the value from the database in column 1
                //then add it to the ArrayList
                listData.add(data.getString(2));
            }
        }else{
            listData.add("Account Log Table is Empty");
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mAccountLogListView.setAdapter(adapter);
    }*/

    private void PopulateListView(){

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
