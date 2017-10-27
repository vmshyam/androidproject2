package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddActivity extends AppCompatActivity {

    private Button btnJvAddProfile;
    private Button btnJvAddPhotos;
    private Button btnjvAddAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


/*        btnJvAddProfile = (Button) findViewById(R.id.btnNavAdminViewSettings);
        btnJvAddPhotos = (Button) findViewById(R.id.btnNavUserDB);
        btnjvAddAdd = (Button) findViewById(R.id.btnNavAddPhotos);

        btnJvAddProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnJvAddPhotos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddActivity.this, PhotosActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
