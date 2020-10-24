package com.example.keychain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String ACCOUNT_INFO = "account_info";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final String PASSWORD = "PASSWORD";

    public Database(@Nullable Context context) {
        super(context, "accounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // called when database is first accessed
        String createTableStatement = "CREATE TABLE " + ACCOUNT_INFO + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + EMAIL + " TEXT, " + PHONE + " TEXT, " + PASSWORD + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // called when database version upgrades

    }

    public boolean checkEmail(AccountModel accountModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        accountModel.setEmail("patlaw");
        Cursor cursor = db.rawQuery("SELECT " + EMAIL + " FROM " + ACCOUNT_INFO + " WHERE " + EMAIL + " = " + accountModel.getEmail(), null);
        if(cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public boolean checkPassword(AccountModel accountModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + PASSWORD + " FROM " + ACCOUNT_INFO + " WHERE " + PASSWORD + "=" + accountModel.getPassword(), null);
        if(cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
    public boolean addAccount(AccountModel accountModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, accountModel.getName());
        cv.put(EMAIL, accountModel.getEmail());
        cv.put(PHONE, accountModel.getPhone());
        cv.put(PASSWORD, accountModel.getPassword());

        long insert = db.insert(ACCOUNT_INFO, null, cv);
        if(insert == -1) {
            return false;
        }

        return true;
    }
}
