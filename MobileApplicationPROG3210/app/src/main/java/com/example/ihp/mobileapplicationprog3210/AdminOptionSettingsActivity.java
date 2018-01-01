package com.example.ihp.mobileapplicationprog3210;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * This Java file is related to 'activity_admin_option_settings'
 * The purpose of this file is to allow the admin to download a image
 *      using input stream.
 */

public class AdminOptionSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnJvAdminSettings, btnJvUserDB, btnJvPhotoDB, btnStartBGService, btnStopBGService;

    TextView tvSignOutClick;
    ImageView imClickDownloadImage;
    ProgressDialog progressDialog;

    private String linkUrl="https://mailchimp.com/assets/images/features/instagram-ads/resources_kb-5b3e7a95.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_option_settings);


        btnJvAdminSettings = (Button) findViewById(R.id.btnALNavAdminSettings);

        btnJvUserDB = (Button) findViewById(R.id.btnALNavUserDB);

        btnJvPhotoDB = (Button) findViewById(R.id.btnALNavPhotoDB);

        btnStartBGService = (Button) findViewById(R.id.btnBgServiceStart);

        btnStopBGService = (Button) findViewById(R.id.btnBgServiceStop);

        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);

        imClickDownloadImage = (ImageView) findViewById(R.id.ivDownloadImage);

        //Navigation button to Home Admin Setting activity
        btnJvAdminSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminOptionSettingsActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        //Navigation button to show all active users of the app activity
        btnJvUserDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminOptionSettingsActivity.this, AdminUserDatabaseActivity.class);
                startActivity(intent);
            }
        });

        //Navigation button to show all the photos added by all users of the app
        btnJvPhotoDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminOptionSettingsActivity.this, AdminPhotoDatabaseActivity.class);
                startActivity(intent);
            }
        });

        //Button to sign out of the app
        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(AdminOptionSettingsActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });

        //Start a Background Service
        btnStartBGService.setOnClickListener(this);

        //Stop a Background Service
        btnStopBGService.setOnClickListener(this);

        imClickDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminOptionSettingsActivity.this,
                        "Downloading Image", Toast.LENGTH_SHORT).show();

                new DownloadImage().execute(linkUrl);

            }
        });


    }


    @Override
    public void onClick(View v) {
        if (v == btnStartBGService){
            startService(new Intent(this, MyService.class));
        } else if (v == btnStopBGService){
            stopService(new Intent(this, MyService.class));
        }
    }

    /** Method used to allow to use Asynchronous Task to download Image from Web **/
    private class DownloadImage extends AsyncTask<String, Void, Bitmap >{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

           progressDialog = new ProgressDialog(AdminOptionSettingsActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlDisplay = strings[0];
            Bitmap imageBM = null;

            try{
                InputStream urlInput = new java.net.URL(urlDisplay).openStream();

                imageBM = BitmapFactory.decodeStream(urlInput);

            }catch (Exception e){
                e.printStackTrace();
            }

            return imageBM;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            imClickDownloadImage.setImageBitmap(bitmap);

            progressDialog.dismiss();
        }

    }

}
