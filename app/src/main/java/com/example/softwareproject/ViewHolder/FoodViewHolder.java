package com.example.softwareproject.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.softwareproject.Interface.ItemClickListener;
import com.example.softwareproject.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView food_name;
    public ImageView food_image;
    private ItemClickListener mItemClickListener2;

    public void setItemClickListener2(ItemClickListener itemClickListener2) {
        mItemClickListener2 = itemClickListener2;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_name = (TextView)itemView.findViewById(R.id.Food_name);
        food_image = (ImageView)itemView.findViewById(R.id.Food_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.mItemClickListener2.onClick(v,getAdapterPosition(),false);

    }
}
