package com.example.ihp.mobileapplicationprog3210;

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

    private String loggedInUserId;
    DatabaseHelper mDatabaseHelper;

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

        populateHomeView();

/*        Toast.makeText(HomeActivity.this,
                "Logged In user Id Value: " + loggedInUserId, Toast.LENGTH_SHORT).show();*/

        btnPhotos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPhotosActivity = new Intent(HomeActivity.this, PhotosActivity.class);
                intentPhotosActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentPhotosActivity);
            }
        });

        btnAddPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentAddActivity = new Intent(HomeActivity.this, AddActivity.class);
                intentAddActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentAddActivity);
            }
        });

    }

    private void populateHomeView(){

        Cursor retrievedUserInfo = mDatabaseHelper.retrieveUserInfoWithId(loggedInUserId);
        while (retrievedUserInfo.moveToNext()){
            tvUsernameView.setText("Hi " + retrievedUserInfo.getString(2));
            tvFullNameView.setText(retrievedUserInfo.getString(1));
            tvEmailView.setText(retrievedUserInfo.getString(3));
        }
    }
}
