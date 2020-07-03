package com.darksuit.owl.instagramui.Helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class RequestHelper {
    private static List<RequestHelper> requests = new ArrayList<>();
    private static int lastRequestCode = 100;
    private static boolean isFirstRequest = true;

    private int requestCode;
    private Activity activity;
    private OnGrantedListener onGrantedListener;
    private OnDeniedListener onDeniedListener;
    private String permission;
    private boolean isFinished;
    private OnAlreadyGrantedListener onAlreadyGrantedListener;

    public interface OnGrantedListener {
        void onGranted();
    }

    public interface OnDeniedListener {
        void onDenied();
    }

    public interface OnAlreadyGrantedListener {
        void onAlreadyGranted();
    }

    public RequestHelper(Activity activity) {
        this.activity = activity;
    }

    public void request(String permission, OnGrantedListener onGrantedListener, OnDeniedListener onDeniedListener, OnAlreadyGrantedListener onAlreadyGrantedListener) {
        requests.add(this);
        lastRequestCode++;
        requestCode = lastRequestCode;

        this.permission = permission;
        this.onAlreadyGrantedListener = onAlreadyGrantedListener;
        this.onGrantedListener = onGrantedListener;
        this.onDeniedListener = onDeniedListener;
        if (isFirstRequest) {
            nextRequest();
            isFirstRequest = false;
        }
    }

    public static void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i("onDenIedrew", "123");
        for (RequestHelper request : requests) {
            if (requestCode == request.requestCode) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (request.onGrantedListener != null) {
                        request.onGrantedListener.onGranted();
                    }
                } else {
                    if (request.onDeniedListener != null) {
                        request.onDeniedListener.onDenied();
                    }
                }

                request.isFinished = true;
                nextRequest();
            }
        }
    }

    public static void nextRequest() {
        for (RequestHelper request : requests) {
            if (!request.isFinished) {
                int granted = ActivityCompat.checkSelfPermission(request.activity, request.permission);
                if (granted == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(request.activity, new String[]{request.permission}, request.requestCode);
                } else if (request.onAlreadyGrantedListener != null) {
                    request.onAlreadyGrantedListener.onAlreadyGranted();
                }
                return;
            }
        }

        isFirstRequest = true;
    }
}