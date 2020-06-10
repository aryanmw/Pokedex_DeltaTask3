package com.wadhavekar.pokedex.TypesOfPokemon;

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

public class RecyclerAdapterForName extends RecyclerView.Adapter<RecyclerAdapterForName.ViewHolder>{
    private ArrayList<String> pokemonNames;


    public RecyclerAdapterForName(ArrayList<String> pokemonNames) {
        this.pokemonNames = pokemonNames;

    }

    @NonNull
    @Override
    public RecyclerAdapterForName.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterForName.ViewHolder holder, int position) {
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










//
//    List<TypesSerialization> pokemons;
//    Context context;
//    int index;
//    ArrayList<String> pokemonInTheGivenType = new ArrayList<>();
//
//
//
//    public RecyclerAdapterForName(int index , Context context , List<TypesSerialization> pokemons) {
//        this.pokemons = pokemons;
//        this.context = context;
//        this.index = index;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerAdapterForName.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
//        return new RecyclerAdapterForName.RecyclerHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerAdapterForName.RecyclerHolder holder, final int position) {
//
//        //final TypesSerialization pokemon = pokemons.get(position);
//
//
//
//        holder.textView.setText(pokemons.get(index).getPokemonNames().get(position).getObjName().getTypePokemonName());
//
//
////
//
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, pokemons.get(position).getPokemonNames().get(0).getObjName().getTypePokemonName(), Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(context,TypesPokemonActivity.class);
////                intent.putExtra("indexForType",position);
//////                intent.putExtra("pokemon_name",pokemons.get(position).getShortName());
//////                intent.putExtra("base_experience",pokemons.get(position).getBaseExperience());
////                context.startActivity(intent);
//            }
//        });
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return pokemons.size();
//    }
//
//public class RecyclerHolder extends RecyclerView.ViewHolder {
//    TextView textView;
//    ImageView imageView;
//    LinearLayout parentLayout;
//
//    public RecyclerHolder(@NonNull View itemView) {
//        super(itemView);
//        textView = itemView.findViewById(R.id.textViewTestAPI);
//        imageView = itemView.findViewById(R.id.imageViewTestAPI);
//        parentLayout = itemView.findViewById(R.id.parent_layout);
//    }
//}
//    public ArrayList<String> getArrayListOfPokemons(){
//        return pokemonInTheGivenType;
//    }
