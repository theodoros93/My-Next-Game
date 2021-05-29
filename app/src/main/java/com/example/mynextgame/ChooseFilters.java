package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.Spinner;

public class ChooseFilters extends AppCompatActivity {
    private Spinner spinner1;

    EditText objEditTextTags;
    private static final String[] paths = {"PC", "Xbox 360", "Playstation 4"};
//    TextView objTextViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_filters);

//
       // addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();



        //Obtain references to objects
        objEditTextTags = findViewById(R.id.editTextTags);
//        objTextViewName = findViewById(R.id.textViewNameNewScreen);

        if (savedInstanceState != null){
            //Retrieve data from the Bundle (other methods include getInt(), getBoolean() etc)
            CharSequence tagsText = savedInstanceState.getCharSequence("savedTagsText");
            //CharSequence displayText = savedInstanceState.getCharSequence("savedDisplayText");

            //Restore the dynamic state of the UI
            objEditTextTags.setText(tagsText);
//            objTextViewName.setText(displayText);
        }
        else{
            //Initialize the UI
            objEditTextTags.setText("");
            objEditTextTags.setHint("example: singleplayer");
//            objTextViewName.setText("TextView");
        }
    }



    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);




    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save data to the Bundle (other methods include putInt(), putBoolean() etc)
        CharSequence tagsText = objEditTextTags.getText();
        outState.putCharSequence("savedTagsText", tagsText);
//        CharSequence displayText = objTextViewName.getText();
//        outState.putCharSequence("savedDisplayText", displayText);
    }

    public void chooseGame(View view){
        //Create the Intent to start the ChooseFilters Activity
        Intent i = new Intent (this , ChooseGame.class);
        //Pass data to the ChooseFilters Activity through the Intent
        CharSequence tagsText = objEditTextTags.getText();
        i.putExtra("savedTagsText", tagsText);

        //Ask Android to start the new Activity
        startActivity(i);
    }
}