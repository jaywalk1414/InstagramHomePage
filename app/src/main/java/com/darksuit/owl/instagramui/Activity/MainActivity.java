package com.darksuit.owl.instagramui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.darksuit.owl.instagramui.Core.G;
import com.darksuit.owl.instagramui.Helper.RequestHelper;
import com.darksuit.owl.instagramui.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpBtmNavView();

        modifyData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setUpBtmNavView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.btmNavView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        NavigationUI.setupWithNavController(bottomNavigationView , navHostFragment.getNavController());
    }

    private void readData() {
        Cursor cursor = G.database.rawQuery("SELECT * FROM instagram ", null);
        int index = 0;
        while(cursor.moveToNext()) {
            index++;
            String personId = cursor.getString(cursor.getColumnIndex("personId"));
            String imgProfile = cursor.getString(cursor.getColumnIndex("imgProfile"));
            String imgPost = cursor.getString(cursor.getColumnIndex("imgPost"));
            String caption = cursor.getString(cursor.getColumnIndex("caption"));
            Log.i("LOG", "#" + index + ": " + personId + " | " + imgProfile + " | "+ imgPost + " | "+ caption );
        }
        cursor.close();
    }

    private void modifyData() {
        try {
            for (int i=0; i<10; i++) {
                String personId = "test" + i;
                String imgProfile = "profile1.jpg";
                String imgPost = "post2.jpg";
                String caption = "why so serious ?";
                String query = "INSERT INTO 'instagram' ('personId','imgProfile','imgPost','caption') VALUES ('" + personId + "', '" + imgProfile + "', '" + imgPost + "' , '" + caption + "')";
                G.database.execSQL(query);
            }
        } catch (SQLiteConstraintException e) {
            Log.e("LOG", e.getMessage());
        }

    }


}
