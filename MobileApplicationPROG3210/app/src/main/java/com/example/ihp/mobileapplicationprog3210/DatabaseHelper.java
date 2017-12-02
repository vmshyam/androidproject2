package com.example.ihp.mobileapplicationprog3210;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This Java file is related to each every Java file that connects to Database.
 * The purpose of this file is to store all related methods to Create, Save and
 *      retrieve database information for the internal connection of the app
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SNAPSTER_DB_V4.db";

    private static final String USERS_TABLE_NAME = "contacts";
    private static final String USERS_COLUMN_ID = "id";
    private static final String USERS_COLUMN_NAME = "name";
    private static final String USERS_COLUMN_USERNAME = "username";
    private static final String USERS_COLUMN_EMAIL = "email";
    private static final String USERS_COLUMN_PASSWORD = "password";

    private static final String ACCOUNT_LOGS_TABLE_NAME = "accountLogs";
    private static final String ACCOUNT_LOGS_COLUMN_ID = "id";
    private static final String ACCOUNT_LOGS_COLUMN_USER_ID = "user_id";
    private static final String ACCOUNT_LOGS_COLUMN_USERNAME = "username";
    private static final String ACCOUNT_LOGS_COLUMN_CURRENT_TIMESTAMP = "timestamp";

    private static final String USER_PHOTO_TABLE_NAME = "userPhotos";
    private static final String USER_PHOTO_COLUMN_ID = "id";
    private static final String USER_PHOTO_COLUMN_USER_ID = "photo_user_id";
    private static final String USER_PHOTO_COLUMN_PHOTO_NAME = "photo_name";
    private static final String USER_PHOTO_COLUMN_PHOTO_TIMESTAMP = "timestamp";
    private static final String USER_PHOTO_COLUMN_PHOTO_IMAGE = "photo_image";

    SQLiteDatabase db;

    private static final String TABLE_CREATE_FOR_USER = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE_NAME + " ("
            + USERS_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, "
            + USERS_COLUMN_NAME + " TEXT NOT NULL, "
            + USERS_COLUMN_USERNAME + " TEXT NOT NULL, "
            + USERS_COLUMN_EMAIL + " TEXT NOT NULL, "
            + USERS_COLUMN_PASSWORD + " TEXT NOT NULL "
            + ");";

    private static final String TABLE_CREATE_FOR_ACCOUNT_LOGS = "CREATE TABLE IF NOT EXISTS " + ACCOUNT_LOGS_TABLE_NAME + " ("
            + ACCOUNT_LOGS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ACCOUNT_LOGS_COLUMN_USER_ID + " TEXT NOT NULL, "
            + ACCOUNT_LOGS_COLUMN_USERNAME + " TEXT NOT NULL, "
            + ACCOUNT_LOGS_COLUMN_CURRENT_TIMESTAMP + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')) "
            + ");";

    private static final String TABLE_CREATE_FOR_USER_PHOTO = "CREATE TABLE IF NOT EXISTS " + USER_PHOTO_TABLE_NAME + " ("
            + USER_PHOTO_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_PHOTO_COLUMN_USER_ID + " TEXT NOT NULL, "
            + USER_PHOTO_COLUMN_PHOTO_NAME + " TEXT NOT NULL, "
            + USER_PHOTO_COLUMN_PHOTO_TIMESTAMP + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')), "
            + USER_PHOTO_COLUMN_PHOTO_IMAGE + " BLOB "
            + ");";

    /** A helper class to manage database creation and version management **/
    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TABLE_CREATE_FOR_USER);

        sqLiteDatabase.execSQL(TABLE_CREATE_FOR_ACCOUNT_LOGS);

        sqLiteDatabase.execSQL(TABLE_CREATE_FOR_USER_PHOTO);

        this.db = sqLiteDatabase;

    }

    /** Method to insert new User account created **/
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

    /** Method to search if user logIn has an account created within the app **/
    public String searchUserNameInDatabase(String userName){

        db = this.getReadableDatabase();

        String query = "SELECT " + USERS_COLUMN_USERNAME + ", "
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

    /** Method to to search user account to if new user creating user name already exists **/
    public boolean searchIfUsernameExists(String username){

        boolean checkValidate = false;

        db = this.getReadableDatabase();

        String query = "SELECT " + USERS_COLUMN_USERNAME + " FROM " + USERS_TABLE_NAME + ";";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){

            if ((cursor.getString(0)).equals(username)){

                checkValidate = true;

                break;

            }
        }

        return checkValidate;

    }

    /** Method to to retrieve particular user information with a given UserId **/
    public Cursor retrieveUserInfoWithId (String userId) {

        db = this.getReadableDatabase();

        Cursor cursor;

        String queryGetUserInfo = "SELECT * FROM "
                + USERS_TABLE_NAME + " WHERE " + USERS_COLUMN_ID + " = '" + userId + "'";

        cursor = db.rawQuery(queryGetUserInfo, null);

        return cursor;

    }

    /** Method to retrieve all user information within the app **/
    public Cursor getRetrieveUserData(){

        SQLiteDatabase dbRetrieve =  this.getWritableDatabase();

        String query = "SELECT * FROM " + USERS_TABLE_NAME;

        Cursor data = dbRetrieve.rawQuery(query, null);

        return data;

    }

    /** Find a particular UserID related to the Username **/
    public int retrieveSelectedUserID(String userName){

        int foundUserID = -1;

        SQLiteDatabase dbGetUserID = this.getWritableDatabase();

        String queryGetUserID = "SELECT " + USERS_COLUMN_ID + " FROM "
                + USERS_TABLE_NAME + " WHERE " + USERS_COLUMN_USERNAME + " = '" + userName + "'";

        Cursor dataRetrieved = dbGetUserID.rawQuery(queryGetUserID, null);

        while (dataRetrieved.moveToNext()){
            foundUserID = dataRetrieved.getInt(0);
        }

        return foundUserID;
    }

    /** Find a particular Username related to the UserId **/
    public String retrieveSelectedUsername(String userId){

        String foundUsername = null;

        SQLiteDatabase dbGetUserName = this.getWritableDatabase();

        String queryGetUsername = "SELECT " + USERS_COLUMN_USERNAME + " FROM "
                + USERS_TABLE_NAME + " WHERE " + USERS_COLUMN_ID + " = '" + userId + "'";

        Cursor dataRetrieved = dbGetUserName.rawQuery(queryGetUsername, null);

        while (dataRetrieved.moveToNext()){
            foundUsername = dataRetrieved.getString(0);
        }

        return foundUsername;

    }

    /** Insert values into Account Log table **/
    public void insertAccountLogData(String userID, String userName){

        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ACCOUNT_LOGS_COLUMN_USER_ID, userID);

        contentValues.put(ACCOUNT_LOGS_COLUMN_USERNAME, userName);


        db.insert(ACCOUNT_LOGS_TABLE_NAME, null, contentValues);

        db.close();
    }


    /** Retrieve all data values from Account Log table **/
    public Cursor getRetrieveAccountLogData(){

        SQLiteDatabase dbRetrieve =  this.getWritableDatabase();

        String query = "SELECT * FROM " + ACCOUNT_LOGS_TABLE_NAME;

        Cursor data = dbRetrieve.rawQuery(query, null);

        return data;
    }

    /** Add/Insert User Photo information into User Photo table **/
    public boolean insertUserPhotoData(String userId, String photoName, byte[] photoImage){

        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_PHOTO_COLUMN_USER_ID, userId);

        contentValues.put(USER_PHOTO_COLUMN_PHOTO_NAME, photoName);

        contentValues.put(USER_PHOTO_COLUMN_PHOTO_IMAGE, photoImage);

        long result = db.insert(USER_PHOTO_TABLE_NAME, null, contentValues);

        db.close();

        if (result == -1){

            return false;

        }else {

            return true;

        }

    }

    /** Method to retrieve all user photo information within the app **/
    public Cursor getRetrieveUserPhotoData(){

        SQLiteDatabase dbRetrieve = this.getWritableDatabase();

        String query = "SELECT * FROM " + USER_PHOTO_TABLE_NAME;

        Cursor data = dbRetrieve.rawQuery(query, null);

        return data;
    }

    /** Method to clear all user accounts within the app **/
    public void DropTableUsers()
    {

        SQLiteDatabase dtUsers = this.getWritableDatabase();

        dtUsers.delete(USERS_TABLE_NAME, null, null);

        dtUsers.close();
    }

    /** Method to clear all information of accounts that loggedIn to the app **/
    public void DropTableAccountLog()
    {

        SQLiteDatabase dtAccountLog = this.getWritableDatabase();

        dtAccountLog.delete(ACCOUNT_LOGS_TABLE_NAME, null, null);

        dtAccountLog.close();
    }

    /** Method to clear all information of all user photos within the app **/
    public void DropTableUserPhotos()
    {

        SQLiteDatabase dtUsersPhoto = this.getWritableDatabase();

        dtUsersPhoto.delete(USER_PHOTO_TABLE_NAME, null, null);

        dtUsersPhoto.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String query1 = "DROP TABLE IF EXISTS " + USERS_TABLE_NAME;

        String query2 = "DROP TABLE IF EXISTS " + ACCOUNT_LOGS_TABLE_NAME;

        String query3 = "DROP TABLE IF EXISTS " + USER_PHOTO_TABLE_NAME;

        db.execSQL(query1);

        db.execSQL(query2);

        db.execSQL(query3);

        this.onCreate(sqLiteDatabase);

    }
}
