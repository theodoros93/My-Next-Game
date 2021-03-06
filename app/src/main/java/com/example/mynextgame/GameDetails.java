package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

            // needing the idText for another API GET, this time to request single node
            extractGame(idText);


        }
    }

    // extracts single node Data (for single game)
     private void extractGame(CharSequence gameID) {
        // volley used for async functionality
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL + gameID + "?key=" + API_KEY , null, response -> {
            try {

                // setting values to game fields
                pickedGame.setID(Integer.parseInt(gameID.toString()));
                pickedGame.setName(response.getString("name"));
                pickedGame.setDescription(response.getString("description"));
                pickedGame.setReleased(response.getString("released"));
                pickedGame.setWebsite(response.getString("website"));
                pickedGame.setRating(Float.parseFloat(response.getString("rating")));
                pickedGame.setPlaytime(Integer.parseInt(response.getString("playtime")));
                // Developers is a JSONArray, acquiring the 1st entry (the main developer of the game)
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

    public void newGameToWishlist (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        String gameID = String.valueOf(pickedGame.getID());
        String gameName = objChosenGameTitle.getText().toString();
        String gameDescription = objChosenGameDescription.getText().toString();
        String gameReleased = objChosenGameReleased.getText().toString();
        String gameRating = objChosenGameRating.getText().toString();
        String gamePlaytime = objChosenGamePlaytime.getText().toString();
        String gameWebsite = objChosenGameWebsite.getText().toString();
        String gameDeveloper = objChosenGameDeveloper.getText().toString();
        String gameImage = pickedGame.getImage();


        if (!gameName.equals("")){
            Game found = dbHandler.findInWishlist(gameName);
            if (found == null){
                Game game = new Game(Integer.parseInt(gameID), gameName, Float.parseFloat(gameRating), Integer.parseInt(gamePlaytime), gameReleased, gameImage, gameDescription, gameDeveloper, gameWebsite);
                dbHandler.addGameToWishlist(game);
                Toast.makeText(this, "Game added to your Wishlist.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Game already in your Wishlist!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void newGameToLibrary (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String gameID = String.valueOf(pickedGame.getID());
        String gameName = objChosenGameTitle.getText().toString();
        String gameDescription = objChosenGameDescription.getText().toString();
        String gameReleased = objChosenGameReleased.getText().toString();
        String gameRating = objChosenGameRating.getText().toString();
        String gamePlaytime = objChosenGamePlaytime.getText().toString();
        String gameWebsite = objChosenGameWebsite.getText().toString();
        String gameDeveloper = objChosenGameDeveloper.getText().toString();
        String gameImage = pickedGame.getImage();


        if (!gameName.equals("")){
            Game found = dbHandler.findInLibrary(gameName);
            if (found == null){
                Game game = new Game(Integer.parseInt(gameID), gameName, Float.parseFloat(gameRating), Integer.parseInt(gamePlaytime), gameReleased, gameImage, gameDescription, gameDeveloper, gameWebsite);
                dbHandler.addGameToLibrary(game);
                Toast.makeText(this, "Game added to your Library.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Game already in your Library!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}