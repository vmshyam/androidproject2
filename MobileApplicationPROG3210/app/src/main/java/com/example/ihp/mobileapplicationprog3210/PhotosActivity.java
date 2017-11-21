package com.example.ihp.mobileapplicationprog3210;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PhotosActivity extends AppCompatActivity {

    private Button btnProfileView;
    private Button btnPhotosView;
    private Button btnPhotosAddView;

    private String loggedInUserId;

    DatabaseHelper mDatabaseHelper;
    public ListView lvMyPhotoView;
    ArrayList<UserPhotoData> listOfPhotosData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        loggedInUserId = getIntent().getStringExtra("LOGGED_IN_USER_ID");

        lvMyPhotoView = (ListView) findViewById(R.id.lvMyPhoto);
        mDatabaseHelper = new DatabaseHelper(this);

        btnProfileView = (Button) findViewById(R.id.btnNavProfile);
        btnPhotosView = (Button) findViewById(R.id.btnNavPhotos);
        btnPhotosAddView = (Button) findViewById(R.id.btnNavAddPhotos);

        populatePhotoView();
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_photos_layout, listOfPhotosData);
        lvMyPhotoView.setAdapter(adapter);

        btnProfileView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentHomeActivity = new Intent(PhotosActivity.this, HomeActivity.class);
                intentHomeActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentHomeActivity);
            }
        });

        btnPhotosAddView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentAddActivity = new Intent(PhotosActivity.this, AddActivity.class);
                intentAddActivity.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
                startActivity(intentAddActivity);
            }
        });



    }

    private void populatePhotoView() {

        Cursor photoDataContent = mDatabaseHelper.getRetrieveUserPhotoData();
        //TODO - Need to change the index location after new database is created
        while (photoDataContent.moveToNext()){
            if ((photoDataContent.getString(1).equals(loggedInUserId))){
                String photoNameData = photoDataContent.getString(2);
                String photoDateStampData = photoDataContent.getString(3);
                byte[] imageData = photoDataContent.getBlob(4);
                listOfPhotosData.add(new UserPhotoData(photoNameData, photoDateStampData, imageData));
            }
        }
    }

    public class CustomAdapter extends BaseAdapter
    {
        private Context context;
        private int layout;
        ArrayList<UserPhotoData> dataList;

        public CustomAdapter(Context context, int layout, ArrayList<UserPhotoData> dataList) {
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
                holder.hPhotoName = row.findViewById(R.id.tvPhotoName);
                holder.hPhotoDate = row.findViewById(R.id.tvDateStamp);
                holder.hPhotoView = row.findViewById(R.id.photoImageView);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final UserPhotoData userPhotoList = dataList.get(position);
            holder.hPhotoName.setText(userPhotoList.getPhotoName());
            holder.hPhotoDate.setText(userPhotoList.getPhotoDateStamp());

            byte[] userImagePhoto = userPhotoList.getPhotoImage();
            final Bitmap bitmap = BitmapFactory.decodeByteArray(userImagePhoto, 0, userImagePhoto.length);
            holder.hPhotoView.setImageBitmap(bitmap);

            return row;
        }
    }
}
