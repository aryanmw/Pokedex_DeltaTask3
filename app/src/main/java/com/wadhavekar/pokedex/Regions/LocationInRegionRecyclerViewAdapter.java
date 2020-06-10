package com.wadhavekar.pokedex.Regions;
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
import com.wadhavekar.pokedex.ItemsOfPokemon;
import com.wadhavekar.pokedex.R;

import java.util.ArrayList;
import java.util.List;

public class LocationInRegionRecyclerViewAdapter extends RecyclerView.Adapter<LocationInRegionRecyclerViewAdapter.ViewHolder>{
    private ArrayList<String> pokemonNames;


    public LocationInRegionRecyclerViewAdapter(ArrayList<String> pokemonNames) {
        this.pokemonNames = pokemonNames;

    }

    @NonNull
    @Override
    public LocationInRegionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        return new LocationInRegionRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationInRegionRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(pokemonNames.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTestAPI);
        }
    }
}