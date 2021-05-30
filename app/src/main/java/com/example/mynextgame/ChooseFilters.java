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

public class ChooseFilters extends AppCompatActivity {
    EditText objEditTextTags;
    EditText objEditTextGenres;
    EditText objEditTextTitle;
    Spinner spinner1;

    // these are needed to pass extras to next activity
    private ArrayList<String> tagsArray = new ArrayList<>();
    private ArrayList<String> genresArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_filters);

        // adding the listheners
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        //Obtain references to objects
        objEditTextTags = findViewById(R.id.editTextTags);
        objEditTextTitle = findViewById(R.id.editTextTitle);
        objEditTextGenres = findViewById(R.id.editTextGenres);

        if (savedInstanceState != null){
            //Retrieve data from the Bundle
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
        }
    }


    // initializes spinner object
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinnerPlatform);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {
        spinner1 = (Spinner) findViewById(R.id.spinnerPlatform);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save data to the Bundle
        CharSequence tagsText = objEditTextTags.getText();
        outState.putCharSequence("savedTagsText", tagsText);
        CharSequence genresText = objEditTextGenres.getText();
        outState.putCharSequence("savedGenresText", genresText);
        CharSequence titleText = objEditTextTitle.getText();
        outState.putCharSequence("savedTitleText", titleText);

    }

    public void chooseGame(View view){
        //Create the Intent to start the ChooseGame Activity
        Intent i = new Intent (this , ChooseGame.class);
        //Pass data to the ChooseFilters Activity through the Intent
        String titleString = objEditTextTitle.getText().toString();

        i.putExtra("savedTagsArray", tagsArray);
        i.putExtra("savedGenresArray", genresArray);
        // getting spinner value
        i.putExtra("savedPlatforms", mapSpinnerItems(spinner1.getSelectedItem().toString()));
        i.putExtra("savedTitleString", titleString);
        //Ask Android to start the new Activity
        startActivity(i);
        // finishing activity to erase all memory of current search -> This means a back press from next activity will return to main activity
        finish();
    }

    // mapping for every string value -> to platform ids that the API accepts
    public String mapSpinnerItems(String selectedItem){
        switch (selectedItem){
            case "PC":
                return "4";
            case "Playstation 4":
                return "18";
            case "Xbox One":
                return "1";
            case "Xbox Series S/X":
                return "186";
            case "Android":
                return "21";
            case "iOS":
                return "3";
        }
        return "";
    }

    // when adding a tag (user presses +)
    public void addTag(View view)
    {
        String newTag = objEditTextTags.getText().toString();
        Toast.makeText(this, "added tag: " + newTag, Toast.LENGTH_SHORT).show();
        tagsArray.add(newTag);
        objEditTextTags.setText("");

    }

    // when adding a genre (user presses +)
    public void addGenre(View view)
    {
        String newGenre = objEditTextGenres.getText().toString();
        Toast.makeText(this, "added Genre: " + newGenre, Toast.LENGTH_SHORT).show();
        genresArray.add(newGenre);
        objEditTextGenres.setText("");
    }
}