package com.example.ihp.mobileapplicationprog3210;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {

    private Button btnJvAddProfile, btnJvAddPhotos, btnjvAddAdd;
    private Button btnCameraPhoto, btnGallaryPhoto, btnAddPhoto;
    EditText etPhotoName;
    ImageView ivMyImage;
    TextView tvSignOutClick;

    DatabaseHelper mDatabaseHelper;

    public static final int REQUEST_CODE_CAMERA = 100;
    public static final int REQUEST_CODE_GALLARY = 200;

    private String loggedInUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        loggedInUserId = getIntent().getStringExtra("LOGGED_IN_USER_ID");
        mDatabaseHelper = new DatabaseHelper(this);

        btnJvAddProfile = (Button) findViewById(R.id.btnNavProfile);
        btnJvAddPhotos = (Button) findViewById(R.id.btnNavPhotos);
        btnjvAddAdd = (Button) findViewById(R.id.btnNavAddPhotos);
        btnCameraPhoto = (Button) findViewById(R.id.btnAddCameraPhoto);
        btnGallaryPhoto = (Button) findViewById(R.id.btnAddGallaryPhoto);
        btnAddPhoto = (Button) findViewById(R.id.btnAddPhoto);

        ivMyImage = (ImageView) findViewById(R.id.ivAddPhotoPreview);

        etPhotoName = (EditText) findViewById(R.id.etPhotoName);
        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);



        btnJvAddProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentHomeActivity = new Intent(AddActivity.this, HomeActivity.class);
                intentHomeActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentHomeActivity);
            }
        });

        btnJvAddPhotos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPhotoActivity = new Intent(AddActivity.this, PhotosActivity.class);
                intentPhotoActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentPhotoActivity);
            }
        });


        btnCameraPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddActivity.this,
                        new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
            }
        });

        btnGallaryPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLARY);
            }
        });

        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtPhotoName =  etPhotoName.getText().toString().trim();
                byte[] newImageEntry = ImageViewToByte(ivMyImage);

                if (txtPhotoName.length() != 0){
                    AddDataTable(txtPhotoName, newImageEntry);
                    Intent intentPhotoActivity = new Intent(AddActivity.this, PhotosActivity.class);
                    intentPhotoActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                    startActivity(intentPhotoActivity);
                }else {
                    Toast("Please Add a Photo Image Name");
                }
            }
        });

        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(AddActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });

    }

    private byte[] ImageViewToByte(ImageView addedImage){

        Bitmap bitmap = ((BitmapDrawable) addedImage.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytesArray = stream.toByteArray();

        return bytesArray;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults){

        if (requestCode == REQUEST_CODE_GALLARY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intentGallary = new Intent(Intent.ACTION_PICK);
                intentGallary.setType("image/*");
                startActivityForResult(intentGallary, REQUEST_CODE_GALLARY);
            }else {
                Toast("You Do Not Have The Permission To Access Gallery");
            }
            return;
        }else if (requestCode == REQUEST_CODE_CAMERA){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera, REQUEST_CODE_CAMERA);
            }else{
                Toast("You Do Not Have The Permission To Access Camera");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        Uri uri = data.getData();
        if (requestCode == REQUEST_CODE_GALLARY){
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);

                int width = bitmapImage.getWidth();
                int height = bitmapImage.getHeight();
                float scaleWidth = ((float) 1000) / width;
                float scaleHeight = ((float) 1000) / height;
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                Bitmap resizedBitmap = Bitmap.createBitmap(bitmapImage, 0, 0, width, height, matrix, true);
                //Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmapImage, 300, 250, true);

                ivMyImage.setImageBitmap(resizedBitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }else if (requestCode == REQUEST_CODE_CAMERA){
            Bitmap bitmapCamera = (Bitmap) data.getExtras().get("data");

            int width = bitmapCamera.getWidth();
            int height = bitmapCamera.getHeight();
            float scaleWidth = ((float) 1000) / width;
            float scaleHeight = ((float) 1000) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmapCamera, 0, 0, width, height, matrix, true);

            //Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmapCamera, 300, 250, true);
            ivMyImage.setImageBitmap(resizedBitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AddDataTable(String newImageName, byte[] newImageEntry){
        //TODO - Make to pass the UserId so that you can added to the Table Below
        String testUserId = "0";
        boolean insertedDataResult = mDatabaseHelper.insertUserPhotoData(loggedInUserId,
                newImageName, newImageEntry);
        if (insertedDataResult){
            Toast("Photo Image Added Successfully");
        }else {
            Toast("Photo Image Did Not Add Successfully");
        }

    }

    private void Toast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
