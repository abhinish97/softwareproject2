package com.example.softwareproject.ViewHolder;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.softwareproject.Interface.ItemClickListener;
import com.example.softwareproject.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView MenuName;
    public ImageView imageView;
    private ItemClickListener mItemClickListener;

    public MenuViewHolder(View itemView) {

        super(itemView);
        MenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
