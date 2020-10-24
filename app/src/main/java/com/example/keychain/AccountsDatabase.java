package com.example.keychain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
Database object that creates and manages the accounts database
 */

public class AccountsDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "account_info";
    public static final String ID = "_id"; // col 1
    public static final String COL_NAME_NAME = "name"; // col 2
    public static final String COL_NAME_EMAIL = "email"; // col 3
    public static final String COL_NAME_PHONE = "phone"; // col 4
    public static final String COL_NAME_PASSWORD = "password"; // col 5
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME_NAME + " VARCHAR(255), " + COL_NAME_EMAIL + " VARCHAR(225), " + COL_NAME_PHONE + " VARCHAR(255), " + COL_NAME_PASSWORD + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public AccountsDatabase(@Nullable Context c) {
        super(c, "accounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase adb) { // called when database is first accessed
        adb.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase adb, int oldVersion, int newVersion) { // called when database version increases
    }

//    public boolean checkEmail(AccountModel am) {
//        SQLiteDatabase adb = this.getReadableDatabase();
//        Cursor cursor = adb.rawQuery("SELECT " + EMAIL + " FROM " + ACCOUNT_INFO + " WHERE " + EMAIL + " = " + am.getEmail(), null);
//        if(cursor.moveToFirst()) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean checkPassword(AccountModel am) {
//        SQLiteDatabase adb = this.getReadableDatabase();
//        Cursor cursor = adb.rawQuery("SELECT " + PASSWORD + " FROM " + ACCOUNT_INFO + " WHERE " + PASSWORD + "=" + am.getPassword(), null);
//        if(cursor.moveToFirst()) {
//            return true;
//        }
//        return false;
//    }
//    public boolean addAccount(AccountModel accountModel) {
//        SQLiteDatabase adb = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(NAME, accountModel.getName());
//        cv.put(EMAIL, accountModel.getEmail());
//        cv.put(PHONE, accountModel.getPhone());
//        cv.put(PASSWORD, accountModel.getPassword());
//
//        long insert = adb.insert(ACCOUNT_INFO, null, cv);
//        if(insert == -1) {
//            return false;
//        }
//        return true;
//    }
}