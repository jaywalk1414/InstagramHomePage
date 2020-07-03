package com.darksuit.owl.instagramui.UI;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darksuit.owl.instagramui.Core.G;
import com.darksuit.owl.instagramui.Helper.RequestHelper;
import com.darksuit.owl.instagramui.InstagramSample;
import com.darksuit.owl.instagramui.MyRecyclerAdapter;
import com.darksuit.owl.instagramui.R;

import java.security.acl.Permission;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container , false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(readData(), getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return rootView;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private ArrayList<InstagramSample> readData() {
        Cursor cursor = G.database.rawQuery("SELECT * FROM instagram ", null);

        ArrayList<InstagramSample> list = new ArrayList<>();
        while(cursor.moveToNext()) {
            String personId = cursor.getString(cursor.getColumnIndex("personId"));
            String imgProfile = cursor.getString(cursor.getColumnIndex("imgProfile"));
            String imgPost = cursor.getString(cursor.getColumnIndex("imgPost"));
            String caption = cursor.getString(cursor.getColumnIndex("caption"));
            InstagramSample sample = new InstagramSample(imgProfile, imgPost,  personId, caption );

            list.add(sample);
        }
        cursor.close();

        return list;
    }
}
