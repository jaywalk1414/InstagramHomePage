package com.darksuit.owl.instagramui.Core;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.darksuit.owl.instagramui.Helper.MyDatabaseHelper;

import java.io.File;


public class G extends Application {
    public static G g;

    public static SQLiteDatabase database;
    public static final String secStore = Environment.getExternalStorageDirectory().getAbsolutePath() ;
    public static final String APP_Dir = secStore + "/CnaIsHere";
    public static final String DB_DIR = APP_Dir + "/dp";

    @Override
    public void onCreate() {
        super.onCreate();

//        createDirectory();
//        createOrUpdateSQL();



        g = this;
    }
    public void createDirectory(){
        File sdCard = new File(DB_DIR);

        if (!sdCard.exists()){
            sdCard.mkdirs();
        }

    }

    public void createOrUpdateSQL(){
        if (database != null)
            return;

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
    }
}
