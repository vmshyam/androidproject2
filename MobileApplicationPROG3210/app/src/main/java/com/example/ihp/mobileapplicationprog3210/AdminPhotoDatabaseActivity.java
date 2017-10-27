package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPhotoDatabaseActivity extends AppCompatActivity {

    private Button btnJvAdminSettings;
    private Button btnJvUserDB;
    private Button btnjvPhotoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_photo_database);

        btnJvAdminSettings = (Button) findViewById(R.id.btnNavAdminSettings);
        btnJvUserDB = (Button) findViewById(R.id.btnNavUserDB);
        btnjvPhotoDB = (Button) findViewById(R.id.btnNavPhotoDB);

        btnJvAdminSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminPhotoDatabaseActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        btnJvUserDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminPhotoDatabaseActivity.this, AdminUserDatabaseActivity.class);
                startActivity(intent);
            }
        });

    }
}
