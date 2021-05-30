package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseFilters extends AppCompatActivity {
    EditText objEditTextTags;
    EditText objEditTextGenres;
    EditText objEditTextTitle;
    Spinner spinner1;

    // these are need to pass extras
    ArrayList<String> tagsArray = new ArrayList<>();
    ArrayList<String> genresArray = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_filters);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        //Obtain references to objects
        objEditTextTags = findViewById(R.id.editTextTags);
        objEditTextTitle = findViewById(R.id.editTextTitle);
        objEditTextGenres = findViewById(R.id.editTextGenres);
//        objTextViewName = findViewById(R.id.textViewNameNewScreen);

        if (savedInstanceState != null){
            //Retrieve data from the Bundle (other methods include getInt(), getBoolean() etc)
            CharSequence tagsText = savedInstanceState.getCharSequence("savedTagsText");
            CharSequence genresText = savedInstanceState.getCharSequence("savedGenresText");
            CharSequence TitleText = savedInstanceState.getCharSequence("savedTitleText");

            //Restore the dynamic state of the UI
            objEditTextTags.setText(tagsText);
            objEditTextTitle.setText(TitleText);
            objEditTextGenres.setText(genresText);
        }
        else{
            //Initialize the UI
            objEditTextTags.setText("");
            objEditTextTitle.setText("");
            objEditTextGenres.setText("");
//            objTextViewName.setText("TextView");
        }
    }



    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinnerPlatform);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        Log.v("tag",spinner1.getSelectedItem().toString());

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinnerPlatform);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save data to the Bundle (other methods include putInt(), putBoolean() etc)
        CharSequence tagsText = objEditTextTags.getText();
        outState.putCharSequence("savedTagsText", tagsText);
        CharSequence genresText = objEditTextGenres.getText();
        outState.putCharSequence("savedGenresText", genresText);
        CharSequence titleText = objEditTextTitle.getText();
        outState.putCharSequence("savedTitleText", titleText);

    }

    public void chooseGame(View view){
        //Create the Intent to start the ChooseFilters Activity
        Intent i = new Intent (this , ChooseGame.class);
        //Pass data to the ChooseFilters Activity through the Intent
        String titleString = objEditTextTitle.getText().toString();

        i.putExtra("savedTagsArray", tagsArray);
        i.putExtra("savedGenresArray", genresArray);
        i.putExtra("savedTitleString", titleString);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //Ask Android to start the new Activity
        startActivity(i);
    }

    // when adding a tag
    public void addTag(View view)
    {
        String newTag = objEditTextTags.getText().toString();
        Toast.makeText(this, "added tag: " + newTag, Toast.LENGTH_SHORT).show();
        tagsArray.add(newTag);
        objEditTextTags.setText("");

    }

    // when adding a genre
    public void addGenre(View view)
    {
        String newGenre = objEditTextGenres.getText().toString();
        Toast.makeText(this, "added Genre: " + newGenre, Toast.LENGTH_SHORT).show();
        genresArray.add(newGenre);
        objEditTextGenres.setText("");
    }
}