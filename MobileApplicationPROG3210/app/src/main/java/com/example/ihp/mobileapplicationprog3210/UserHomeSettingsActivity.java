package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This Java file is related to 'activity_user_home_settings'
 * The purpose of this file is to allow the user to send
 *      Admin a personal email message. And also allow
 *      user to view app developer Website link.
 */

public class UserHomeSettingsActivity extends AppCompatActivity {

    private Button btnSendEmail;
    private Button btnAdminPage;

    private Button btnProfile;
    private Button btnPhotos;
    private Button btnAddPhoto;

    private EditText edSubjectLine;
    private EditText edMultLineEmailContent;
    private String loggedInUserId;

    private TextView tvSignOutClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_settings);

        loggedInUserId = getIntent().getStringExtra("LOGGED_IN_USER_ID");

        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnAdminPage = (Button) findViewById(R.id.btnAboutAdmin);

        btnProfile = (Button) findViewById(R.id.btnNavProfile);
        btnPhotos = (Button) findViewById(R.id.btnNavPhotos);
        btnAddPhoto = (Button) findViewById(R.id.btnNavAddPhotos);

        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);

        edSubjectLine = (EditText) findViewById(R.id.etEmailSubject);
        edMultLineEmailContent = (EditText) findViewById(R.id.etEmailContent);

        //Button to allow user to send a personal email to Admin
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailSubject = edSubjectLine.getText().toString();
                String emailContent = edMultLineEmailContent.getText().toString();

                Intent emailClient = new Intent(Intent.ACTION_SEND);
                String[] emailTo = {"vshyam-cc@conestogac.on.ca"};
                emailClient.putExtra(Intent.EXTRA_EMAIL, emailTo);
                emailClient.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                emailClient.putExtra(Intent.EXTRA_TEXT, emailContent);
                emailClient.setType("message/rfc822");
                startActivity(Intent.createChooser(emailClient, "Choose Email Client To Use"));
                edSubjectLine.getText().clear();
                edMultLineEmailContent.getText().clear();
            }
        });

        //Button to allow user to view Admin About Page
        btnAdminPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent openAdminPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vmshyam.github.io/"));
                startActivity(openAdminPage);
            }
        });

        //Navigation button to view all photos added by user
        btnProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentHomeActivity = new Intent(UserHomeSettingsActivity.this, HomeActivity.class);
                intentHomeActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentHomeActivity);
            }
        });

        //Navigation button to view all photos added by user
        btnPhotos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPhotosActivity = new Intent(UserHomeSettingsActivity.this, PhotosActivity.class);
                intentPhotosActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentPhotosActivity);
            }
        });

        //Navigation button to allow user to add new photos to the photo collection
        btnAddPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentAddActivity = new Intent(UserHomeSettingsActivity.this, AddActivity.class);
                intentAddActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentAddActivity);
            }
        });

        //Button to sign out of the app
        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(UserHomeSettingsActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });

    }
}
