package com.darksuit.owl.instagramui.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.darksuit.owl.instagramui.Core.G;
import com.darksuit.owl.instagramui.Helper.RequestHelper;
import com.darksuit.owl.instagramui.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        requestForPermission();
    }

    private void requestForPermission(){
        final RequestHelper requestHelper = new RequestHelper(SplashActivity.this);
        requestHelper.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {
                G.g.createDirectory();
                G.g.createOrUpdateSQL();
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                Log.i("ondenied", "ok");
                new AlertDialog.Builder(SplashActivity.this)
                        .setTitle("Permission Needed!!")
                        .setMessage("This app need this Permission to continue ")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestForPermission();
                            }
                        })
                        .create()
                        .show();
            }
        }, new RequestHelper.OnAlreadyGrantedListener() {
            @Override
            public void onAlreadyGranted() {
                G.g.createDirectory();
                G.g.createOrUpdateSQL();
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestHelper.onRequestPermissionResult(requestCode, permissions, grantResults);
    }
}
