package com.example.mynextgame;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    LayoutInflater inflater;
    List<Game> games;

    public Adapter(Context ctx, List<Game> games) {
        this.inflater = LayoutInflater.from(ctx);
        this.games = games;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // binding the data
        holder.gameTitle.setText(games.get(position).getName());
        holder.gameRating.setText(String.valueOf(games.get(position).getRating()));
        holder.gameReleased.setText(games.get(position).getReleased());
        holder.gameID.setText(String.valueOf(games.get(position).getID()));
        Picasso.get().load(games.get(position).getImage()).into(holder.gameImage);

    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView gameTitle, gameRating, gameReleased, gameID;
        ImageView gameImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameTitle = itemView.findViewById(R.id.gameTitle);
            gameRating = itemView.findViewById(R.id.gameRating);
            gameReleased = itemView.findViewById(R.id.gameReleased);
            gameImage = itemView.findViewById(R.id.gameImage);
            gameID = itemView.findViewById(R.id.gameIdHelper);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext(), GameDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("savedID", String.valueOf(gameID.getText()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
