package com.example.thisisourapponepointo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import io.realm.RealmList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private RealmList<EBoardMemberItem> mDataset;
    static Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView img;
        public DataObjectHolder(View itemView) {
            super(itemView);
            itemView.getContext();
            context = itemView.getContext();
            title = (TextView) itemView.findViewById(R.id.eboard_member_card_title);
            description = (TextView) itemView.findViewById(R.id.eboard_member_card_description);
            img = itemView.findViewById(R.id.eboard_member_card_picture);
        }
    }

    public MyRecyclerViewAdapter(RealmList<EBoardMemberItem> myDataset, Context c) {
        mDataset = myDataset;
        context = c;

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board_info_card_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());
        holder.description.setText(mDataset.get(position).getDescription());
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(mDataset.get(position).getImage(), 0, mDataset.get(position).getImage().length);
            holder.img.setImageBitmap(bitmap);
        }catch (Exception e){
            Toast.makeText(context, "FILE NOT FOUND", Toast.LENGTH_SHORT).show();
        }

    }

    public void addItem(EBoardMemberItem dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}