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

public class AdminUserDatabaseActivity extends AppCompatActivity {

    private Button btnJvAdminSettings;
    private Button btnJvUserDB;
    private Button btnJvPhotoDB;

    private static final String TAG = "";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_database);

        btnJvAdminSettings = (Button) findViewById(R.id.btnNavAdminSettings);
        btnJvUserDB = (Button) findViewById(R.id.btnNavUserDB);
        btnJvPhotoDB = (Button) findViewById(R.id.btnNavPhotoDB);

        mListView = (ListView) findViewById(R.id.lvShowUserDB);
        mDatabaseHelper = new DatabaseHelper(this);

        PopulateListView();
        
        btnJvAdminSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminUserDatabaseActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        btnJvPhotoDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminUserDatabaseActivity.this, AdminPhotoDatabaseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void PopulateListView() {
        Log.d(TAG, "populateListView");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getRetrieveUserData();

        ArrayList<String> listData = new ArrayList<>();

        if (data.getCount() !=0) {
            while (data.moveToNext()){
                //get the value from the database in column 1
                //then add it to the ArrayList
                listData.add(data.getString(1));
            }
        }else {
            listData.add("Account User Table is Empty");
        }
        
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }
}
