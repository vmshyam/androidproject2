package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminAccountLogsActivity extends AppCompatActivity {

    private Button btnJvAdminSettings, btnJvUserDB, btnJvPhotoDB;

    private static final String TAG = "";

    DatabaseHelper mDatabaseHelper;

    private ListView mAccountLogListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account_logs);

        mDatabaseHelper = new DatabaseHelper(this);

        btnJvAdminSettings = (Button) findViewById(R.id.btnALNavAdminSettings);
        btnJvUserDB = (Button) findViewById(R.id.btnALNavUserDB);
        btnJvPhotoDB = (Button) findViewById(R.id.btnALNavPhotoDB);

        mAccountLogListView = (ListView) findViewById(R.id.lvShowAccountLogDB);


        PopulateListView();

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
    }


    private void PopulateListView() {
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
    }
}
