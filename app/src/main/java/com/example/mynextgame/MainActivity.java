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

    public void chooseFilters(View view){
        //Create the Intent to start the ChooseFilters Activity
        Intent i = new Intent (this , ChooseFilters.class);
//        //Pass data to the ChooseFilters Activity through the Intent
//        CharSequence userText = objEditTextName.getText
//        i.putExtra savedUserText "", userText

        //Ask Android to start the new Activity
        startActivity(i);
    }
}