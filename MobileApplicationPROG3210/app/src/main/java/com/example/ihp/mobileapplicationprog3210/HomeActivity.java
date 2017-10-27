package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button btnJvHomeProfile;
    private Button btnJvHomePhotos;
    private Button btnjvHomeAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

/*        btnJvHomeProfile = (Button) findViewById(R.id.btnNavAdminViewSettings);
        btnJvHomePhotos = (Button) findViewById(R.id.btnNavUserDB);
        btnjvHomeAdd = (Button) findViewById(R.id.btnNavAddPhotos);

        btnJvHomePhotos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, PhotosActivity.class);
                startActivity(intent);
            }
        });

        btnjvHomeAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });*/

    }
}
