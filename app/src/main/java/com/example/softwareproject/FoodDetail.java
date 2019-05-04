package com.example.softwareproject;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.softwareproject.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {

        TextView Food_name,Food_price,Food_description;
        ImageView Food_image;
        CollapsingToolbarLayout mCollapsingToolbarLayout;
        FloatingActionButton btnCart;
        ElegantNumberButton numberbtn;
        String food_id ="";
        FirebaseDatabase mDatabase;
        DatabaseReference foods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        mDatabase = FirebaseDatabase.getInstance();
        foods = mDatabase.getReference("Food");
        numberbtn = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btn_cart);
        Food_description = (TextView)findViewById(R.id.food_discription);
        Food_price = (TextView)findViewById(R.id.food_price);
        Food_name = (TextView)findViewById(R.id.food_name);
        Food_image = (ImageView)findViewById(R.id.image_food) ;

        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collasping);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppbar);

        if(getIntent()!=null)
        {
            food_id = getIntent().getStringExtra("FoodId");
        }
        if(!food_id.isEmpty())
        {
            getDetailFood(food_id);
        }
    }

    private void getDetailFood(String food_id) {

        foods.child(food_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);
                Picasso.with(getBaseContext()).load(food.getImage()).into(Food_image);
                mCollapsingToolbarLayout.setTitle(food.getName());
                Food_price.setText(food.getPrice());
                Food_name.setText(food.getName());
                Food_description.setText(food.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
