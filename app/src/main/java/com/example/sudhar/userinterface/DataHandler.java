package com.example.sudhar.userinterface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sudhar on 14-01-2016.
 package com.example.silly;

 import android.content.ContentValues;
 import android.content.Context;
 import android.database.Cursor;
 import android.database.sqlite.SQLiteDatabase;
 import android.database.sqlite.SQLiteOpenHelper;

 /**
 * Created by RAJESH on 11-12-2015.
 */
public class DataHandler {
    public static final String phone1 = "ph1";
    public static final String phone2 = "ph2";
    public static final String msg= "msg";
    public static final String TABLE_NAME = "mytable";
    public static final String DATABASE_NAME = "mydatabase";
    public static final int DATABASE_VERSION = Integer.parseInt("1");
    public static final String TABLE_CREATE="create table mytable(ph1 text not null,ph2 text not null,msg text not null);";
    DatabaseHelper dbhelper;
    Context ctx;
    SQLiteDatabase sqLiteDatabase;

    public DataHandler(Context ctx) {
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

    public DataHandler open()
    {

        sqLiteDatabase = dbhelper.getWritableDatabase();
        return this;
    }

    public void close() {

        dbhelper.close();
    }

    public long insertData(String ph1, String ph2,String texts) {
        ContentValues values = new ContentValues();
        values.put(phone1, ph1);
        values.put(phone2, ph2);
        values.put(msg, texts);
        return sqLiteDatabase.insertOrThrow(TABLE_NAME, null, values);
    }

    public Cursor returndata() {
        return sqLiteDatabase.query(TABLE_NAME, new String[]{phone1,phone2,msg}, null, null, null, null, null);

    }
}

