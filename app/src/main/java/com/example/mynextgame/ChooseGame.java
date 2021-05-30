package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChooseGame extends AppCompatActivity {
//    TextView objTextViewTags;

    RecyclerView recyclerView;
    List<Game> games;
    private static final String JSON_URL = "https://api.rawg.io/api/games";
    private static final String API_KEY = "b8d66c33ab0245c38f23f3ea321c1fb5";
    Adapter adapter;

    // initializing bundle extras
    private ArrayList<String> genresArray = new ArrayList<>();
    private ArrayList<String> tagsArray = new ArrayList<>();
    private String titleString = "";
    private String platformString="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        //Get Bundle from the Intent
        Bundle extras = getIntent().getExtras();

        //If there are data passed in the Intent
        if (extras != null) {
            //Retrieve data passed in the Intent
            tagsArray = extras.getStringArrayList("savedTagsArray");
            genresArray = extras.getStringArrayList("savedGenresArray");
            titleString = extras.getString("savedTitleString");
            platformString = extras.getString("savedPlatforms");
            Log.v("tag",platformString);

        }

        recyclerView = findViewById(R.id.gamesList);
        games = new ArrayList<>();
        extractGames(titleString);
    }


    private void extractGames(String titleString) {


        // converting arrays to string
        // also adding the needed elements for a JSON GET, according to API specifications
        for (int i = 0; i<tagsArray.size();i++){
            tagsArray.set(i,"&tags="+tagsArray.get(i));
        }
        String tagsString =String.join("", tagsArray);
        Log.v("tagsString",tagsString);

        for (int i = 0; i<genresArray.size();i++){
            genresArray.set(i,"&genre="+genresArray.get(i));
        }
        String genresString =String.join("", genresArray);
        Log.v("genreString",genresString);

        // empty platform string is not accepted by the rawg.io API
        String url;
        if (!platformString.equals("")){
            url = JSON_URL + "?key=" + API_KEY + tagsString + genresString + "&search=" + titleString + "&platforms=" + platformString;
        }
        else{
            url = JSON_URL + "?key=" + API_KEY + tagsString + genresString + "&search=" + titleString;
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            // the games are under response.results (the Jsonarray list)
            try {
                JSONArray resultArray = response.getJSONArray("results");
                for (int i = 0; i < resultArray.length(); i++) {
                    try {
                        JSONObject gameObject = resultArray.getJSONObject(i);
                        Game game = new Game();
                        game.setName(gameObject.getString("name"));
                        game.setPlaytime(Integer.parseInt(gameObject.getString("playtime")));
                        game.setRating(Float.parseFloat(gameObject.getString("rating")));
                        game.setReleased(gameObject.getString("released"));
                        game.setImage(gameObject.getString("background_image"));
                        game.setID(Integer.parseInt(gameObject.getString("id")));
                        games.add(game);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            JSONArray ja = jsonObj.getJSONArray("Ingredients");
//            int len = ja.length();

            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter = new Adapter(getApplicationContext(), games);
            recyclerView.setAdapter(adapter);

        }, error -> Log.d("APItag", "onErrorResponse: "+ error.getMessage()));

        queue.add(jsonObjectRequest);
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