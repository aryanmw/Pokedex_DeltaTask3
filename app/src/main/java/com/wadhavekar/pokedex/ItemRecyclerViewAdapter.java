package com.wadhavekar.pokedex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.RecyclerHolder>{
    List<ItemsOfPokemon> pokemons;
    Context context;


    public ItemRecyclerViewAdapter(Context context , List<ItemsOfPokemon> pokemons) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int position) {
        final ItemsOfPokemon pokemon = pokemons.get(position);
        holder.textView.setText(pokemons.get(position).getItemName());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(pokemon.getItemSprites().getItemUrl()).into(holder.imageView);
        //models.get(position).getFront_default()

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, pokemons.get(position).getAbility().getAbilityNames(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context,MoreInformation.class);
//                intent.putExtra("image_url",pokemon.getSprites().getFrontDefault());
//                intent.putExtra("pokemon_name",pokemons.get(position).getShortName());
//                intent.putExtra("base_experience",pokemons.get(position).getBaseExperience());
//                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout parentLayout;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTestAPI);
            imageView = itemView.findViewById(R.id.imageViewTestAPI);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}

