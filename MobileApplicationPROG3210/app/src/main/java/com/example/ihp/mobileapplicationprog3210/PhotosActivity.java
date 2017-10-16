package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhotosActivity extends AppCompatActivity {

    private Button btnJvPhotosProfile;
    private Button btnJvPhotosPhotos;
    private Button btnjvPhotosAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        btnJvPhotosProfile = (Button) findViewById(R.id.btnPhotosProfile);
        btnJvPhotosPhotos = (Button) findViewById(R.id.btnPhotosPhotos);
        btnjvPhotosAdd = (Button) findViewById(R.id.btnPhotosAdd);

        btnJvPhotosProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PhotosActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnjvPhotosAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PhotosActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }
}
