package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminSettingsActivity extends AppCompatActivity {

    private Button btnJvAdminSettings, btnJvUserDB,
            btnjvPhotoDB, btndtUserTable, btnViewAccontLog, btndtAccountLogTable, btndtUserPhotoTable;


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
        btndtAccountLogTable = (Button) findViewById(R.id.btnDeleteAccoutLogTable);
        btndtUserPhotoTable = (Button) findViewById(R.id.btnDropUserPhotoTable);

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
                Toast.makeText(AdminSettingsActivity.this,
                        "Successfully Cleared User Account Table", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewAccontLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminSettingsActivity.this, AdminAccountLogsActivity.class);
                startActivity(intent);
            }
        });

        btndtAccountLogTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDatabaseHelper.DropTableAccountLog();
                Toast.makeText(AdminSettingsActivity.this,
                        "Successfully Cleared Account Log Table", Toast.LENGTH_SHORT).show();
            }
        });

        btndtUserPhotoTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDatabaseHelper.DropTableUserPhotos();
                Toast.makeText(AdminSettingsActivity.this,
                        "Successfully Cleared User Photos Table", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
