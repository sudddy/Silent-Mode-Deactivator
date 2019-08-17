package com.example.sudhar.userinterface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sudhar on 30-01-2016.
 */
public class DataHandlerOne {
    public static final String first = "msg1";
    public static final String second = "msg2";

    public static final String TABLE_NAME = "mytable1";
    public static final String DATABASE_NAME = "mydatabase1";
    public static final int DATABASE_VERSION = Integer.parseInt("1");
    public static final String TABLE_CREATE = "create table mytable1(msg1 text not null,msg2 text not null);";
    DatabaseHelper dbhelper;
    Context ctx;
    SQLiteDatabase sqLiteDatabase;

    public DataHandlerOne(Context ctx) {
        this.ctx = ctx;
        dbhelper = new DatabaseHelper(ctx);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context ctx) {

            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(TABLE_CREATE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mytable ");
            onCreate(sqLiteDatabase);

        }
    }

    public DataHandlerOne open() {

        sqLiteDatabase = dbhelper.getWritableDatabase();
        return this;
    }

    public void close() {

        dbhelper.close();
    }

    public long insertData(String msg1, String msg2) {
        ContentValues values = new ContentValues();
        values.put(first, msg1);
        values.put(second, msg2);

        return sqLiteDatabase.insertOrThrow(TABLE_NAME, null, values);
    }

    public Cursor returndata() {
        return sqLiteDatabase.query(TABLE_NAME, new String[]{first, second}, null, null, null, null, null);

    }
}
