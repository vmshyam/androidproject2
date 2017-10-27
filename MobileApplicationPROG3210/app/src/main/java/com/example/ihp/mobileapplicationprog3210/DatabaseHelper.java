package com.example.ihp.mobileapplicationprog3210;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SNAPSTER_DB.db";

    private static final String USERS_TABLE_NAME = "contacts";
    private static final String USERS_COLUMN_ID = "id";
    private static final String USERS_COLUMN_NAME = "name";
    private static final String USERS_COLUMN_USERNAME = "username";
    private static final String USERS_COLUMN_EMAIL = "email";
    private static final String USERS_COLUMN_PASSWORD = "password";

    private static final String ACCOUNT_LOGS_TABLE_NAME = "account_logs";
    private static final String ACCOUNT_LOGS_COLUMN_ID = "id";
    private static final String ACCOUNT_LOGS_COLUMN_USER_ID = "user_id";
    private static final String ACCOUNT_LOGS_COLUMN_USERNAME = "username";
    private static final String ACCOUNT_LOGS_COLUMN_CURRENT_TIMESTAMP = "timestamp";



    SQLiteDatabase db;
    //TODO Need to check if ad ID into string query for USER_COLUMN_ID effects results below
    private static final String TABLE_CREATE_FOR_USER = "CREATE TABLE " + USERS_TABLE_NAME + " ("
            + USERS_COLUMN_ID + " ID INTEGER PRIMARY KEY NOT NULL, "
            + USERS_COLUMN_NAME + " TEXT NOT NULL, "
            + USERS_COLUMN_USERNAME + " TEXT NOT NULL, "
            + USERS_COLUMN_EMAIL + " TEXT NOT NULL, "
            + USERS_COLUMN_PASSWORD + " TEXT NOT NULL "
            + ");";

    private static final String TABLE_CREATE_FOR_ACCOUNT_LOGS = "CREATE TABLE " + ACCOUNT_LOGS_TABLE_NAME + " ("
            + ACCOUNT_LOGS_COLUMN_ID + " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + ACCOUNT_LOGS_COLUMN_USER_ID + " TEXT NOT NULL, "
            + ACCOUNT_LOGS_COLUMN_USERNAME + " TEXT NOT NULL, "
            + ACCOUNT_LOGS_COLUMN_CURRENT_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP "
            + ");";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE_FOR_USER);
        this.db = sqLiteDatabase;
    }

    public void insertContact(Contact c){
        db =  this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String queryForId = "SELECT * FROM " + USERS_TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(queryForId, null);
        int tableRowCount = cursor.getCount();

        values.put(USERS_COLUMN_ID, tableRowCount);
        values.put(USERS_COLUMN_NAME, c.getGivenName());
        values.put(USERS_COLUMN_USERNAME, c.getUserName());
        values.put(USERS_COLUMN_EMAIL, c.getEmailAddress());
        values.put(USERS_COLUMN_PASSWORD, c.getPassword());

        db.insert(USERS_TABLE_NAME, null, values);
        db.close();
    }

    public String searchUserNameInDatabase(String userName){

        db = this.getReadableDatabase();
        String query = "SELECT " + USERS_COLUMN_NAME + ", "
                + USERS_COLUMN_PASSWORD + " FROM " + USERS_TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        String queriedUsername;
        String queriedPassword = "NOT FOUND";

        if (cursor.moveToFirst())
        {
            do {
                queriedUsername = cursor.getString(0);
                if (queriedUsername.equals(userName)) {
                    queriedPassword = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }

        return queriedPassword;
    }

    public Cursor getRetrieveUserData(){
        SQLiteDatabase dbRetrieve =  this.getWritableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE_NAME;
        Cursor data = dbRetrieve.rawQuery(query, null);
        return data;
    }

    /** Insert values in to Account Log table **/
    public void insertAccoutLogData(String userID, String userName){

    }


    /** Retrieve all data values from Account Log table **/
    public Cursor getRetrieveAccountLogData(){
        SQLiteDatabase dbRetrieve =  this.getWritableDatabase();
        String query = "SELECT * FROM " + ACCOUNT_LOGS_TABLE_NAME;
        Cursor data = dbRetrieve.rawQuery(query, null);
        return data;
    }


    public void DropTableUsers()
    {
        SQLiteDatabase dtUsers = this.getWritableDatabase();
        dtUsers.delete(USERS_TABLE_NAME, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + USERS_TABLE_NAME;
        db.execSQL(query);
        this.onCreate(sqLiteDatabase);
    }
}
