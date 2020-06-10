package com.wadhavekar.pokedex.FavouritePokemons;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.wadhavekar.pokedex.DatabaseManager.DatabaseAdapter;
import com.wadhavekar.pokedex.R;

import java.util.ArrayList;

public class FavouritePokemonsDisplayActivity extends AppCompatActivity {
    ListView myListView;
    ArrayList<FavPokemon> favPokemons;
    FavPokemon fav;
    DatabaseAdapter myDB;
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_pokemons_display);
        myListView = findViewById(R.id.listView_favs);
        myDB = new DatabaseAdapter(this);
        favPokemons = new ArrayList<>();
        Cursor data = myDB.getNameAndURL();
        if (data.getCount() == 0){
            Toast.makeText(this, "No entries yet", Toast.LENGTH_SHORT).show();
        }
        else{
            while(data.moveToNext()){
                fav = new FavPokemon(data.getString(1),data.getString(2));
                favPokemons.add(fav);
            }
            listViewAdapter = new ListViewAdapter(this,R.layout.layout_row,favPokemons);
            myListView.setAdapter(listViewAdapter);
        }
    }
}
