package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ChooseGame extends AppCompatActivity {
    TextView objTextViewTags;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        //Obtain references to objects
//        objTextViewTags = (TextView) findViewById(R.id.textViewTags);

        //Get Bundle from the Intent
        Bundle extras = getIntent().getExtras();

        //If there are data passed in the Intent
        if (extras != null) {
            //Retrieve data passed in the Intent
            CharSequence tagsText = extras.getCharSequence("savedTagsText");

            //For debugging: print in the Logact (Debug level)
            Log.d("SayHelloNewScreen.java",tagsText.toString());

            //Update the UI
//            objTextViewTags.setText("You passed the tag: " + tagsText);
        }
    }


//    public void chooseGame(View view){
//        //Create the Intent to start the ChooseFilters Activity
//        Intent i = new Intent (this , ChooseFilters.class);
//        //Pass data to the ChooseFilters Activity through the Intent
//        CharSequence userText = objEditTextName.getText
//        i.putExtra savedUserText "", userText
//
//        //Ask Android to start the new Activity
//        startActivity(i);
}