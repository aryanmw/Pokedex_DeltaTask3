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
import com.wadhavekar.pokedex.Pokemon;
import com.wadhavekar.pokedex.R;

import java.util.ArrayList;
import java.util.List;

public class TypesRecyclerViewAdapter extends RecyclerView.Adapter<TypesRecyclerViewAdapter.RecyclerHolder>{
    List<TypesSerialization> pokemons;
    Context context;
    ArrayList<String> pokemonInTheGivenType = new ArrayList<>();
    public TypesRecyclerViewAdapter(){

    }


    public TypesRecyclerViewAdapter(Context context , List<TypesSerialization> pokemons) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public TypesRecyclerViewAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        return new TypesRecyclerViewAdapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypesRecyclerViewAdapter.RecyclerHolder holder, final int position) {
        final TypesSerialization pokemon = pokemons.get(position);
        holder.textView.setText(pokemons.get(position).getTypeName());
       // holder.id.setText(pokemons.get(position).getTypeId());
//        if (pokemonInTheGivenType.size() > 0) {
//            pokemonInTheGivenType.clear();
//        }
//        for (int i = 0 ; i < pokemons.get(position).getPokemonNames().size() ; i++){
//            pokemonInTheGivenType.add(pokemons.get(position).getPokemonNames().get(i).getObjName().getTypePokemonName());

        //}
//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(pokemon.getItemSprites().getItemUrl()).into(holder.imageView);
        //models.get(position).getFront_default()


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TypesPokemonActivity.class);
                for (int i = 0 ; i < pokemons.get(position).getPokemonNames().size() ; i++){
                    pokemonInTheGivenType.add(pokemons.get(position).getPokemonNames().get(i).getObjName().getTypePokemonName());
                }
                intent.putStringArrayListExtra("myArrayList",pokemonInTheGivenType);
                intent.putExtra("TypeName",pokemons.get(position).getTypeName());
                //Toast.makeText(context, pokemons.get(position).getPokemonNames().get(0).getObjName().getTypePokemonName() + " " + pokemonInTheGivenType.size(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
                pokemonInTheGivenType.clear();

              //intent.put
//                intent.putExtra("indexForType",position);
////                intent.putExtra("pokemon_name",pokemons.get(position).getShortName());
////                intent.putExtra("base_experience",pokemons.get(position).getBaseExperience());


            }
        });
    }



    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView,id;
        ImageView imageView;
        LinearLayout parentLayout;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTestAPI);
            imageView = itemView.findViewById(R.id.imageViewTestAPI);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            //id = itemView.findViewById(R.id.textIdTestAPI);
        }
    }
    public ArrayList<String> getArrayListOfPokemons(){
        return pokemonInTheGivenType;
    }
    public void filterList(ArrayList<TypesSerialization> filteredPokemonList){
        pokemons = filteredPokemonList;
        notifyDataSetChanged();

    }
}