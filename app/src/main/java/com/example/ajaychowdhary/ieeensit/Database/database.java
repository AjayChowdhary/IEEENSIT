package com.example.ajaychowdhary.ieeensit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AJAY CHOWDHARY on 24-01-2017.
 */

public class database extends SQLiteOpenHelper {
    public static String DATABASE_NAME="feeds.db";
    public static String TABLE_NAME="from_facebook";

    private Context context;
    public database(Context context) {
        super(context,DATABASE_NAME,null,1);
      this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(message TEXT,link TEXT,image TEXT,postid TEXT,likes TEXT,date TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
}
