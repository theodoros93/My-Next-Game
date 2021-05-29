package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameDetails extends AppCompatActivity {

    private static final String JSON_URL = "https://api.rawg.io/api/games/";
    private static final String API_KEY = "b8d66c33ab0245c38f23f3ea321c1fb5";
    TextView objTextView2;
    Game pickedGame = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        //Obtain references to objects
        objTextView2 = (TextView) findViewById(R.id.textView2);

        //Get Bundle from the Intent
        Bundle extras = getIntent().getExtras();

        //If there are data passed in the Intent
        if (extras != null) {
            //Retrieve data passed in the Intent
            CharSequence idText = extras.getCharSequence("savedID");

            //For debugging: print in the Logact (Debug level)
            Log.d("SayHelloNewScreen.java",idText.toString());

            //Update the UI
            objTextView2.setText("You passed the tag: " + idText);
            extractGame(idText);


        }
    }

     private void extractGame(CharSequence gameID) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL + gameID + "?key=" + API_KEY , null, response -> {
            try {

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
                objTextView2.setText(pickedGame.getDeveloper());

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Log.d("APItag", "onErrorResponse: "+ error.getMessage()));

         queue.add(jsonObjectRequest);
    }

}