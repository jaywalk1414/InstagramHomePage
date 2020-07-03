package com.darksuit.owl.instagramui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.RecAdap> {
    private ArrayList<InstagramSample> list;
    private Context context;


    public MyRecyclerAdapter(ArrayList<InstagramSample> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecAdap onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler,parent,false);
        return new RecAdap(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdap holder, int position) {
        InstagramSample item = list.get(position);
        try
        {
            InputStream ims = context.getAssets().open(item.imgProfile);
            Drawable d = Drawable.createFromStream(ims, null);
            holder.imgPro.setImageDrawable(d);
            ims .close();
            InputStream ims2 = context.getAssets().open(item.imgPost);
            Drawable d2 = Drawable.createFromStream(ims2, null);
            holder.imgPost.setImageDrawable(d2);
            ims .close();
        }
        catch(IOException ex){
            Log.i("LOG", ex.getMessage());
        }
        holder.caption.setText(item.caption);
        holder.personId.setText(item.personId);
        holder.personId2.setText(item.personId);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecAdap extends RecyclerView.ViewHolder{
        public ImageView imgPro;
        public ImageView imgPost;
        public TextView personId;
        public TextView personId2;
        public TextView caption ;

        public RecAdap(@NonNull View itemView) {
            super(itemView);

            imgPro = itemView.findViewById(R.id.imageViewProfile);
            imgPost = itemView.findViewById(R.id.imageViewPost);
            personId = itemView.findViewById(R.id.textPersonId);
            personId2 = itemView.findViewById(R.id.textPersonId2);
            caption = itemView.findViewById(R.id.textCaption);

        }


    }

}
