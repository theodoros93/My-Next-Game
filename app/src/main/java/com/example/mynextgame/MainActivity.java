package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewWishlist(View view){
        //Create the Intent to start the ViewList Activity
        Intent i = new Intent (this , ViewWishlist.class);

        //Ask Android to start the new Activity
        startActivity(i);
    }

    public void chooseFilters(View view){
        //Create the Intent to start the ChooseFilters Activity
        Intent i = new Intent (this , ChooseFilters.class);

        //Ask Android to start the new Activity
        startActivity(i);
    }
}