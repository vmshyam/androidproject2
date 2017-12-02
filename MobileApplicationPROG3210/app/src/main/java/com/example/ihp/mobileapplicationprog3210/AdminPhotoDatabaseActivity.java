package com.example.ihp.mobileapplicationprog3210;

/**
 * This Java file is related to 'activity_admin_photo_database.xml'
 * The purpose of this file is to allow the Admin of the app to
 *      view all the photos every user of the app has added
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminPhotoDatabaseActivity extends AppCompatActivity {

    private Button btnJvAdminSettings;

    private Button btnJvUserDB;

    private Button btnjvPhotoDB;

    TextView tvSignOutClick;

    DatabaseHelper mDatabaseHelper;

    public ListView lvUserPhotos;

    ArrayList<UserPhotoActivityData> listOfPhotosData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_photo_database);

        mDatabaseHelper = new DatabaseHelper(this);

        lvUserPhotos = (ListView) findViewById(R.id.lvUserPhoto);

        btnJvAdminSettings = (Button) findViewById(R.id.btnNavAdminSettings);

        btnJvUserDB = (Button) findViewById(R.id.btnNavUserDB);

        btnjvPhotoDB = (Button) findViewById(R.id.btnNavPhotoDB);

        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);

        //Method used to populate the List view of this related activity to show user photo information to Admin
        populatePhotoView();

        //Required to use custom adapter view to show Admin a custom list view experience
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_user_photos_activity_layout, listOfPhotosData);

        lvUserPhotos.setAdapter(adapter);

        //Navigation button to Home Admin Setting activity
        btnJvAdminSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminPhotoDatabaseActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        //Navigation button to show all active users of the app activity
        btnJvUserDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminPhotoDatabaseActivity.this, AdminUserDatabaseActivity.class);
                startActivity(intent);
            }
        });

        //Button to sign out of the app
        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(AdminPhotoDatabaseActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });

    }

    /** Method used to populate the List view of this related activity to show user photo information to Admin  **/
    private void populatePhotoView() {

        Cursor photoDataContent = mDatabaseHelper.getRetrieveUserPhotoData();

        while (photoDataContent.moveToNext()) {

            String usernameData = mDatabaseHelper.retrieveSelectedUsername(photoDataContent.getString(1));

            String photoNameData = photoDataContent.getString(2);

            String photoDateStampData = photoDataContent.getString(3);

            byte[] imageData = photoDataContent.getBlob(4);

            listOfPhotosData.add(new UserPhotoActivityData(usernameData, photoNameData, photoDateStampData, imageData));

        }
    }

    /** Method used to allow the use of custom layout activity to display custom formatted views **/
    public class CustomAdapter extends BaseAdapter {

        private Context context;

        private int layout;

        ArrayList<UserPhotoActivityData> dataList;

        public CustomAdapter(Context context, int layout, ArrayList<UserPhotoActivityData> dataList) {

            this.context = context;

            this.layout = layout;

            this.dataList = dataList;
        }

        @Override
        public int getCount(){

            return dataList.size();

        }

        @Override
        public Object getItem(int position){

            return dataList.get(position);

        }

        @Override
        public long getItemId(int position){

            return position;

        }

        private class ViewHolder{

            TextView hUsername;

            TextView hPhotoName;

            TextView hPhotoDate;

            ImageView hPhotoView;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){

            View row = view;

            ViewHolder holder;

            if(row == null){

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

                row = inflater.inflate(layout, null);

                holder = new ViewHolder();

                holder.hUsername = row.findViewById(R.id.tvUsername);

                holder.hPhotoName = row.findViewById(R.id.tvPhotoName);

                holder.hPhotoDate = row.findViewById(R.id.tvDateStamp);

                holder.hPhotoView = row.findViewById(R.id.photoImageView);

                row.setTag(holder);
            } else {

                holder = (ViewHolder) row.getTag();

            }

            final UserPhotoActivityData userPhotoList = dataList.get(position);

            holder.hUsername.setText(userPhotoList.getUsername());

            holder.hPhotoName.setText(userPhotoList.getPhotoName());

            holder.hPhotoDate.setText(userPhotoList.getPhotoDateStamp());


            byte[] userImagePhoto = userPhotoList.getPhotoImage();

            final Bitmap bitmap = BitmapFactory.decodeByteArray(userImagePhoto, 0, userImagePhoto.length);

            holder.hPhotoView.setImageBitmap(bitmap);

            return row;

        }
    }
}
