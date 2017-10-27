package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminSettingsActivity extends AppCompatActivity {

    private Button btnJvAdminSettings, btnJvUserDB, btnjvPhotoDB, btndtUserTable, btnViewAccontLog;


    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        mDatabaseHelper = new DatabaseHelper(this);
        btnJvAdminSettings = (Button) findViewById(R.id.btnNavAdminSettings);
        btnJvUserDB = (Button) findViewById(R.id.btnNavUserDB);
        btnjvPhotoDB = (Button) findViewById(R.id.btnNavPhotoDB);
        btndtUserTable = (Button) findViewById(R.id.btnDropUserTable);
        btnViewAccontLog = (Button) findViewById(R.id.btnViewAccountLogs);

        btnJvUserDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminSettingsActivity.this, AdminUserDatabaseActivity.class);
                startActivity(intent);
            }
        });

        btnjvPhotoDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminSettingsActivity.this, AdminPhotoDatabaseActivity.class);
                startActivity(intent);
            }
        });

        btndtUserTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.DropTableUsers();
            }
        });

        btnViewAccontLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminSettingsActivity.this, AdminAccountLogsActivity.class);
                startActivity(intent);
            }
        });


    }

}
