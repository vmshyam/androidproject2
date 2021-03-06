package com.example.ihp.mobileapplicationprog3210;

/**
 * This Java file is related to 'activity_home.xml'
 * The purpose of this file is to the landing activity once a user
 *      signs in to the app. Provide user information.
 */

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private Button btnProfile;

    private Button btnPhotos;

    private Button btnAddPhoto;

    private TextView tvUsernameView;

    private TextView tvFullNameView;

    private TextView tvEmailView;

    private TextView tvSignOutClick;

    private String loggedInUserId;

    DatabaseHelper mDatabaseHelper;

    private  TextView tvSettingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loggedInUserId = getIntent().getStringExtra("LOGGED_IN_USER_ID");

        mDatabaseHelper = new DatabaseHelper(this);

        btnProfile = (Button) findViewById(R.id.btnNavProfile);

        btnPhotos = (Button) findViewById(R.id.btnNavPhotos);

        btnAddPhoto = (Button) findViewById(R.id.btnNavAddPhotos);

        tvUsernameView = (TextView) findViewById(R.id.tvDateStamp);

        tvFullNameView = (TextView) findViewById(R.id.tvUsernameTag);

        tvEmailView = (TextView) findViewById(R.id.tvEmail);

        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);

        tvSettingActivity = (TextView) findViewById(R.id.tvSettings);

        //Method used to populate given user information such as Username, Given Name, and Email
        populateHomeView();

        //Navigation button to view all photos added by user
        btnPhotos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPhotosActivity = new Intent(HomeActivity.this, PhotosActivity.class);
                intentPhotosActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentPhotosActivity);
            }
        });

        //Navigation button to allow user to add new photos to the photo collection
        btnAddPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentAddActivity = new Intent(HomeActivity.this, AddActivity.class);
                intentAddActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentAddActivity);
            }
        });

        //Button to sign out of the app
        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });

        //Button to allow user to access settings activity to send emails and view developers website
        tvSettingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettingActivity = new Intent(HomeActivity.this, UserHomeSettingsActivity.class);
                intentSettingActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentSettingActivity);
            }
        });

    }

    /** Method used to populate given user information such as Username, Given Name, and Email **/
    private void populateHomeView(){

        Cursor retrievedUserInfo = mDatabaseHelper.retrieveUserInfoWithId(loggedInUserId);

        while (retrievedUserInfo.moveToNext()){

            tvUsernameView.setText("Hi " + retrievedUserInfo.getString(2));

            tvFullNameView.setText(retrievedUserInfo.getString(1));

            tvEmailView.setText(retrievedUserInfo.getString(3));

        }
    }
}
