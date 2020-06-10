package com.wadhavekar.pokedex.FavouritePokemons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.wadhavekar.pokedex.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<FavPokemon> {
        private LayoutInflater mLayoutInflater;
        private ArrayList<FavPokemon> favPokemons;
    private int mViewResourceId;
    Context context;

    public ListViewAdapter(Context context,int viewResourceId,ArrayList<FavPokemon> favPokemons){
        super(context,viewResourceId,favPokemons);
        this.context = context;
        this.favPokemons = favPokemons;
        this.mViewResourceId = viewResourceId;
        mLayoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

public View getView(int position, View ConvertView, ViewGroup parent){
    ConvertView =mLayoutInflater.inflate(R.layout.layout_row,null);
    FavPokemon fav =favPokemons.get(position);
    if (fav != null){
        ImageView iv = ConvertView.findViewById(R.id.imageViewTestAPI);
        TextView tv = ConvertView.findViewById(R.id.textViewTestAPI);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(fav.getImageURL()).into(iv);
        tv.setText(fav.getPokemonName());
    }
    return ConvertView;
}


}
