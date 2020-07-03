package com.darksuit.owl.instagramui.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.darksuit.owl.instagramui.Core.G;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "sample.sqlite";
    public static final int DB_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, G.DB_DIR + "/" + DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createPersonSQL(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    private void createPersonSQL(SQLiteDatabase db){
        String query = "CREATE TABLE 'instagram' " +
                "( 'personId' TEXT PRIMARY KEY NOT NULL " +
                ", 'imgProfile' TEXT , 'imgPost' TEXT , 'caption' TEXT )";
        db.execSQL(query);
    }
}

