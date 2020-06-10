package com.wadhavekar.pokedex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerHolder>{
    List<Pokemon> pokemons;
    String abilitiesOfPokemons = "";
    Context context;
    String allStats;
    String allTypes;



    public RecyclerViewAdapter(Context context , List<Pokemon> pokemons) {
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
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final Pokemon pokemon = pokemons.get(position);
        holder.textView.setText(pokemons.get(position).getShortName());
        //holder.id.setText(pokemons.get(position).getPokemonId());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(pokemon.getSprites().getFrontDefault()).into(holder.imageView);
        abilitiesOfPokemons = "";
        holder.imageView.setTransitionName("pokemonImage_transition");
        Pair<View,String> pair1 = Pair.create((View) holder.imageView , holder.imageView.getTransitionName());


        //models.get(position).getFront_default()

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStats = "";
                allTypes = "";
                //Toast.makeText(context, pokemons.get(position).getAbility().get(0).getAllAbilities().getAbilityName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,MoreInformation.class);
                intent.putExtra("image_url",pokemon.getSprites().getFrontDefault());
                intent.putExtra("pokemon_name",pokemons.get(position).getShortName());
                intent.putExtra("base_experience",pokemons.get(position).getBaseExperience());
                intent.putExtra("Abilities",  pokemons.get(position).getAbility().get(0).getAllAbilities().getAbilityName());
                for (int i = 0 ; i < pokemons.get(position).getStats().size() ; i++){
                    if (i < pokemons.get(position).getStats().size() - 1){
                        allStats = allStats + pokemons.get(position).getStats().get(i).getStatName().getPokemonStatName() + "(" + pokemons.get(position).getStats().get(i).getStatValue() +  "), ";
                    }
                    else{
                        allStats = allStats + pokemons.get(position).getStats().get(i).getStatName().getPokemonStatName()+ "(" + pokemons.get(position).getStats().get(i).getStatValue() +  ")";
                    }
                }
                intent.putExtra("Stats",allStats);

                for (int i = 0 ; i < pokemons.get(position).getTypeForMoreInfo().size(); i++){
                    if (i < pokemons.get(position).getTypeForMoreInfo().size() - 1){
                        allTypes = allTypes + pokemons.get(position).getTypeForMoreInfo().get(i).getTypeName().getPokemonTypeName() +  ", ";
                    }
                    else{
                        allTypes = allTypes + pokemons.get(position).getTypeForMoreInfo().get(i).getTypeName().getPokemonTypeName();
                    }
                }
                intent.putExtra("Types",allTypes);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.imageView,holder.imageView.getTransitionName());

                context.startActivity(intent,options.toBundle());
                allStats = "";
            }
        });
    }



    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void filterList(ArrayList<Pokemon> filteredPokemonList){
        pokemons = filteredPokemonList;
        notifyDataSetChanged();

    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView,id;
        ImageView imageView;

        LinearLayout parentLayout;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTestAPI);
            imageView = itemView.findViewById(R.id.imageViewTestAPI);
            id = itemView.findViewById(R.id.textIdTestAPI);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
