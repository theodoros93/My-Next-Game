package com.example.mynextgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ViewLibrary extends AppCompatActivity {


    RecyclerView recyclerView;
    List<Game> games;
    Adapter adapter;
    MyDBHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_library);

        // initializing
        recyclerView = findViewById(R.id.gamesLibrary);
        games = new ArrayList<>();
        myDb = new MyDBHandler(this, null, null, 1);

        bindDBDataToRecycler();
    }


    // stores the data in ArrayList, then binds to recyclerview items
    void bindDBDataToRecycler(){
        Cursor cursor = myDb.readAllDataLibrary();
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
        // setting callback to recyclerview
        new ItemTouchHelper((itemTouchHelperCallback)).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }
    // using SimpleCallBack swipe functionality (only for swipe right action on Recyclerview Item)
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Game game = new Game();
            game = games.get(viewHolder.getAdapterPosition());
            // referencing the adapter, because inside it is the viewholder, holding every game item
            // removing the RecyclerView item
            games.remove(viewHolder.getAdapterPosition());
            // also deleting the game from DB
            myDb.deleteFromLibrary(game.getName());
            adapter.notifyDataSetChanged();
        }
    };


}