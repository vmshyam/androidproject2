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
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_USERNAME + " TEXT NOT NULL, "
            + COLUMN_EMAIL + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL "
            + ");";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.db = sqLiteDatabase;
    }

    public void insertContact(Contact c){
        db =  this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String queryForId = "SELECT * FROM " + TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(queryForId, null);
        int tableRowCount = cursor.getCount();

        values.put(COLUMN_ID, tableRowCount);
        values.put(COLUMN_NAME, c.getGivenName());
        values.put(COLUMN_USERNAME, c.getUserName());
        values.put(COLUMN_EMAIL, c.getEmailAddress());
        values.put(COLUMN_PASSWORD, c.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchUserNameInDatabase(String userName){

        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_NAME + ", "
                + COLUMN_PASSWORD + " FROM " + TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        String queriedUsername;
        String querieddPassword = "NOT FOUND";

        if (cursor.moveToFirst())
        {
            do {
                queriedUsername = cursor.getString(0);
                if (queriedUsername.equals(userName)) {
                    querieddPassword = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }

        return querieddPassword;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(sqLiteDatabase);
    }
}
