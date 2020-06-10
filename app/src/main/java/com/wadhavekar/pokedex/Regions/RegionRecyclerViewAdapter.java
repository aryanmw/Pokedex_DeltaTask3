package com.wadhavekar.pokedex.Regions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.wadhavekar.pokedex.TypesOfPokemon.TypesPokemonActivity;
import com.wadhavekar.pokedex.TypesOfPokemon.TypesSerialization;

import java.util.ArrayList;
import java.util.List;

public class RegionRecyclerViewAdapter extends RecyclerView.Adapter<RegionRecyclerViewAdapter.RecyclerHolder>{
    List<RegionSerialization> pokemons;
    Context context;
    ArrayList<String> locationInTheGivenRegion = new ArrayList<>();



    public RegionRecyclerViewAdapter(Context context , List<RegionSerialization> pokemons) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public RegionRecyclerViewAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        return new RegionRecyclerViewAdapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionRecyclerViewAdapter.RecyclerHolder holder, final int position) {
        final RegionSerialization pokemon = pokemons.get(position);
        holder.textView.setText(pokemons.get(position).getRegionName());
        //holder.id.setText(pokemons.get(position).getRegionId());
//        if (pokemonInTheGivenType.size() > 0) {
//            pokemonInTheGivenType.clear();
//        }

//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(pokemon.getItemSprites().getItemUrl()).into(holder.imageView);
        //models.get(position).getFront_default()


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LocationsInRegionActivity.class);
                for (int i = 0 ; i < pokemons.get(position).getPokemonLocations().size() ; i++){
                            locationInTheGivenRegion.add(pokemons.get(position).getPokemonLocations().get(i).getLocationName());

                     }
                intent.putExtra("RegionName",pokemons.get(position).getRegionName());
               intent.putStringArrayListExtra("myLocationList",locationInTheGivenRegion);
               // Toast.makeText(context, pokemons.get(position).getPokemonLocations().get(0).getLocationName() + " " , Toast.LENGTH_SHORT).show();
               context.startActivity(intent);
               locationInTheGivenRegion.clear();

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
        TextView textView;
        ImageView imageView;
        LinearLayout parentLayout;
        EditText id;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTestAPI);
            imageView = itemView.findViewById(R.id.imageViewTestAPI);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            //id = itemView.findViewById(R.id.textIdTestAPI);
        }
    }

    public void filterList(ArrayList<RegionSerialization> filteredPokemonList){
        pokemons = filteredPokemonList;
        notifyDataSetChanged();

    }

}
