package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class AdminAccountLogsActivity extends AppCompatActivity {

    private Button btnJvAdminSettings, btnJvUserDB, btnJvPhotoDB;

    private static final String TAG = "";

    DatabaseHelper mDatabaseHelper;

    private ListView mAccountLogListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account_logs);

        btnJvAdminSettings = (Button) findViewById(R.id.btnALNavAdminSettings);
        btnJvUserDB = (Button) findViewById(R.id.btnALNavUserDB);
        btnJvPhotoDB = (Button) findViewById(R.id.btnALNavPhotoDB);

        mAccountLogListView = (ListView) findViewById(R.id.lvShowAccountLogDB);
        mDatabaseHelper = new DatabaseHelper(this);

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


}
