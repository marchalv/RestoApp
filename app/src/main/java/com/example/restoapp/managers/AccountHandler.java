package com.example.restoapp.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.restoapp.models.Account;

public class AccountHandler extends SQLiteHelper {
    private SQLiteHelper dbHelper;
    public AccountHandler(Context context) {
        super(context);
        dbHelper = new SQLiteHelper(context);
    }

    public int addAccount (Account account) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_NAME, account.getName());
        values.put(dbHelper.KEY_EMAIL, account.getEmail());
        values.put(dbHelper.KEY_PASSWORD, account.getPassword());
        long insertId = db.insert(dbHelper.TABLE_ACCOUNTS, null, values);
        db.close();
        return (int)insertId;
    }

    public Account getAccount(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbHelper.TABLE_ACCOUNTS, new String[] { dbHelper.KEY_ID, dbHelper.KEY_NAME, dbHelper.KEY_EMAIL, dbHelper.KEY_EMAIL }, dbHelper.KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Account account = new Account(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return account;
    }

    public int updateAccount(Account account){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_NAME,account.getName());
        values.put(dbHelper.KEY_EMAIL,account.getEmail());
        values.put(dbHelper.KEY_PASSWORD,account.getPassword());
        long updateId=db.update(dbHelper.TABLE_ACCOUNTS,values, dbHelper.KEY_ID + " = ?", new String[]{String.valueOf(account.getId())});
        db.close();
        return (int) updateId;
    }

    public int getAccountId(String email) {
        String query = "SELECT id FROM accounts WHERE email = '" + email + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        Log.d("ID", String.valueOf(id));
        return id;
    }

    public boolean checkAccount(String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {dbHelper.KEY_ID};
        String selection = dbHelper.KEY_EMAIL + "=?" + " and " + dbHelper.KEY_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(dbHelper.TABLE_ACCOUNTS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
