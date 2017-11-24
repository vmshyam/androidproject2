package com.example.ihp.mobileapplicationprog3210;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminUserDatabaseActivity extends AppCompatActivity {

    private Button btnJvAdminSettings;
    private Button btnJvUserDB;
    private Button btnJvPhotoDB;

    TextView tvSignOutClick;

    private static final String TAG = "";

    DatabaseHelper mDatabaseHelper;

    private ListView lvUserDetailsView;
    ArrayList<UserDetailData> listOfUserDetailsData =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_database);

        mDatabaseHelper = new DatabaseHelper(this);

        btnJvAdminSettings = (Button) findViewById(R.id.btnNavAdminSettings);
        btnJvUserDB = (Button) findViewById(R.id.btnNavUserDB);
        btnJvPhotoDB = (Button) findViewById(R.id.btnNavPhotoDB);

        lvUserDetailsView = (ListView) findViewById(R.id.lvShowUserDB);
        tvSignOutClick = (TextView) findViewById(R.id.tvSignOut);

        PopulateListView();
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_user_display_layout, listOfUserDetailsData);
        lvUserDetailsView.setAdapter(adapter);

        
        btnJvAdminSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminUserDatabaseActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        btnJvPhotoDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminUserDatabaseActivity.this, AdminPhotoDatabaseActivity.class);
                startActivity(intent);
            }
        });

        tvSignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(AdminUserDatabaseActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });
    }

/*    private void PopulateListView() {
        Log.d(TAG, "populateListView");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getRetrieveUserData();

        ArrayList<String> listData = new ArrayList<>();

        if (data.getCount() !=0) {
            while (data.moveToNext()){
                //get the value from the database in column 1
                //then add it to the ArrayList
                listData.add(data.getString(1));
            }
        }else {
            listData.add("Account User Table is Empty");
        }
        
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }*/

    private void PopulateListView(){

        Cursor allUserDetailsContent = mDatabaseHelper.getRetrieveUserData();

        while (allUserDetailsContent.moveToNext()){
            listOfUserDetailsData.add(new UserDetailData(allUserDetailsContent.getString(1),
                    allUserDetailsContent.getString(2),
                    allUserDetailsContent.getString(3)));
        }
    }

    public class CustomAdapter extends BaseAdapter
    {

        private Context context;
        private int layout;
        ArrayList<UserDetailData> dataList;

        public CustomAdapter(Context context, int layout, ArrayList<UserDetailData> dataList) {
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
            TextView hFullName;
            TextView hUsername;
            TextView hEmail;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){

            View row = view;

            ViewHolder holder;

            if(row == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout, null);
                holder = new CustomAdapter.ViewHolder();
                holder.hFullName = row.findViewById(R.id.tvUsername);
                holder.hUsername = row.findViewById(R.id.tvDateStamp);
                holder.hEmail = row.findViewById(R.id.tvEmail);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final UserDetailData userDetailsList = dataList.get(position);

            holder.hFullName.setText(userDetailsList.getFullName());
            holder.hUsername.setText(userDetailsList.getUsername());
            holder.hEmail.setText(userDetailsList.getEmail());

            return row;
        }

    }


}
