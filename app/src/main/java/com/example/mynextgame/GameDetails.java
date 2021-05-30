package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameDetails extends AppCompatActivity {

    private static final String JSON_URL = "https://api.rawg.io/api/games/";
    private static final String API_KEY = "b8d66c33ab0245c38f23f3ea321c1fb5";
    TextView objTextView2;
    TextView objChosenGameTitle;
    TextView objChosenGameDescription;
    TextView objChosenGameReleased;
    TextView objChosenGameRating;
    TextView objChosenGamePlaytime;
    TextView objChosenGameWebsite;
    TextView objChosenGameDeveloper;
    ImageView objChosenGameImage;

    Game pickedGame = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        //Obtain references to objects
        objChosenGameTitle = (TextView) findViewById(R.id.chosenGameTitle);
        objChosenGameDescription = (TextView) findViewById(R.id.chosenGameDescription);
        objChosenGameReleased = (TextView) findViewById(R.id.chosenGameReleased);
        objChosenGameRating = (TextView) findViewById(R.id.chosenGameRating);
        objChosenGamePlaytime = (TextView) findViewById(R.id.chosenGamePlaytime);
        objChosenGameWebsite = (TextView) findViewById(R.id.chosenGameWebsite);
        objChosenGameDeveloper = (TextView) findViewById(R.id.chosenGameDeveloper);
        objChosenGameImage = findViewById(R.id.chosenGameImage);


        //Get Bundle from the Intent
        Bundle extras = getIntent().getExtras();

        //If there are data passed in the Intent
        if (extras != null) {
            //Retrieve data passed in the Intent
            CharSequence idText = extras.getCharSequence("savedID");

            //For debugging: print in the Logact (Debug level)
            Log.d("SayHelloNewScreen.java",idText.toString());

            extractGame(idText);


        }
    }

     private void extractGame(CharSequence gameID) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL + gameID + "?key=" + API_KEY , null, response -> {
            try {

                // setting values to game fields
                pickedGame.setName(response.getString("name"));
                pickedGame.setDescription(response.getString("description"));
                pickedGame.setReleased(response.getString("released"));
                pickedGame.setWebsite(response.getString("website"));
                pickedGame.setRating(Float.parseFloat(response.getString("rating")));
                pickedGame.setPlaytime(Integer.parseInt(response.getString("playtime")));
                JSONArray jaDevs = response.getJSONArray("developers");
                JSONObject devObject = jaDevs.getJSONObject(0);
                pickedGame.setDeveloper(devObject.getString("name"));
                pickedGame.setImage(response.getString("background_image"));


                //Update the UI
                objChosenGameTitle.setText(pickedGame.getName());
                objChosenGameDescription.setText(Html.fromHtml(pickedGame.getDescription()));
                objChosenGameReleased.setText(pickedGame.getReleased());
                objChosenGameWebsite.setText(pickedGame.getWebsite());
                objChosenGameRating.setText(String.valueOf(pickedGame.getRating()));
                objChosenGamePlaytime.setText(String.valueOf(pickedGame.getPlaytime()));
                objChosenGameDeveloper.setText(pickedGame.getDeveloper());
                Picasso.get().load(pickedGame.getImage()).into(objChosenGameImage);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Log.d("APItag", "onErrorResponse: "+ error.getMessage()));

         queue.add(jsonObjectRequest);
    }

}