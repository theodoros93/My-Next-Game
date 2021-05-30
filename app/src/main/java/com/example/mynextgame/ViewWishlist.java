package com.example.mynextgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewWishlist extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Game> games;
    Adapter adapter;
    MyDBHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wishlist);

        // initializing
        recyclerView = findViewById(R.id.gamesWishlist);
        games = new ArrayList<>();
        myDb = new MyDBHandler(this, null, null, 1);

        bindDBDataToRecycler();

    }

    // stores the data in ArrayList, then binds to recyclerview items
    void bindDBDataToRecycler(){
        Cursor cursor = myDb.readAllDataWishlist();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "You haven't added anything yet.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                Game game = new Game();
                game.setID(Integer.parseInt(cursor.getString(0)));
                game.setName(cursor.getString(1));
                game.setRating(Float.parseFloat(cursor.getString(2)));
                game.setReleased(cursor.getString(4));
                game.setImage(cursor.getString(5));
                Log.v("tagView", game.getReleased());
                games.add(game);
            }
        }
        // binding the database data to RecyclerView Items
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new Adapter(getApplicationContext(), games);
        recyclerView.setAdapter(adapter);
    }

}