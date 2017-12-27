package com.example.ihp.mobileapplicationprog3210;
/**
 * This Java file is related to 'activity_admin_settings.xml'
 * The purpose of this file is to direct the Admin to the home
 *      screen of Admin account. Admin UI of central control.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminSettingsActivity extends AppCompatActivity {

    private Button btnJvAdminSettings,
            btnJvUserDB,
            btnjvPhotoDB,
            btndtUserTable,
            btnViewAccontLog,
            btndtAccountLogTable,
            btndtUserPhotoTable;

    private TextView tvSignOutClick, tvOptionalSettings;

    DatabaseHelper mDatabaseHelper;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        mAuth = FirebaseAuth.getInstance();

        mDatabaseHelper = new DatabaseHelper(this);

        btnJvAdminSettings = (Button) findViewById(R.id.btnNavAdminSettings);

        btnJvUserDB = (Button) findViewById(R.id.btnNavUserDB);

        btnjvPhotoDB = (Button) findViewById(R.id.btnNavPhotoDB);

        btndtUserTable = (Button) findViewById(R.id.btnDropUserTable);

        btnViewAccontLog = (Button) findViewById(R.id.btnBgServiceStart);

        btndtAccountLogTable = (Button) findViewById(R.id.btnDeleteAccoutLogTable);

        //btndtUserPhotoTable = (Button) findViewById(R.id.btnDropUserPhotoTable);

        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);

        tvOptionalSettings = (TextView) findViewById(R.id.tvOptionalSettings);

        //Navigation button to show all active users of the app activity
        btnJvUserDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminSettingsActivity.this, AdminUserDatabaseActivity.class);
                startActivity(intent);
            }
        });

        //Navigation button to show all the photos added by all users of the app
        btnjvPhotoDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminSettingsActivity.this, AdminPhotoDatabaseActivity.class);
                startActivity(intent);
            }
        });

        //Button to allow the Admin to delete all existing User Accounts and related User Photo Collection
        btndtUserTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseHelper.DropTableUsers();

                mDatabaseHelper.DropTableUserPhotos();

                Toast.makeText(AdminSettingsActivity.this,
                        "Successfully Cleared User Account Table", Toast.LENGTH_SHORT).show();
            }
        });

        //Navigation button to show all user how loggedIn to the app
        btnViewAccontLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminSettingsActivity.this, AdminAccountLogsActivity.class);
                startActivity(intent);
            }
        });

        //Button to allow the Admin to delete all logged user loggedIn activity from the app
        btndtAccountLogTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDatabaseHelper.DropTableAccountLog();
                Toast.makeText(AdminSettingsActivity.this,
                        "Successfully Cleared Account Log Table", Toast.LENGTH_SHORT).show();
            }
        });

/*        btndtUserPhotoTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDatabaseHelper.DropTableUserPhotos();
                Toast.makeText(AdminSettingsActivity.this,
                        "Successfully Cleared User Photos Table", Toast.LENGTH_SHORT).show();
            }
        });*/

        //Button to sign out of the app
        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(AdminSettingsActivity.this,
                        "Signed Out of Admin Account", Toast.LENGTH_SHORT).show();
                Intent intentSignOut = new Intent(AdminSettingsActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });

        tvOptionalSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(AdminSettingsActivity.this, AdminOptionSettingsActivity.class);
                startActivity(intentSignOut);
            }
        });
    }

}
