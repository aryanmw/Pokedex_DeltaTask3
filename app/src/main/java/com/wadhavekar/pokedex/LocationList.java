package com.wadhavekar.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationList extends AppCompatActivity {
    RecyclerView recyclerView;
    int currentId = 1;
    private List<LocationsOfPokemon> pokemons = new ArrayList<>();
    public static final int LIMIT_ID = 10147;
    public static final int START_GAP = 802;
    public static final int FINAL_GAP = 10001;
    LocationRecyclerViewAdapter adapter;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new LocationRecyclerViewAdapter(this,pokemons);
        setContentView(R.layout.activity_list_of_items);
        recyclerView = findViewById(R.id.rvMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(LocationList.this));
        recyclerView.setAdapter(adapter);
        tv = findViewById(R.id.error);



        LocationPokemonService retrofitService = RetrofitInstance.getRetrofitInstance().create(LocationPokemonService.class);
        int finalId = detectGap(10);
        for(;currentId <= finalId ; currentId++){
            retrofitService.getPokemonLocation(currentId).enqueue(new Callback<LocationsOfPokemon>() {
                @Override
                public void onResponse(Call<LocationsOfPokemon> call, Response<LocationsOfPokemon> response) {
                    pokemons.add(response.body());
                    //Collections.sort(pokemons);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<LocationsOfPokemon> call, Throwable t) {
                    Toast.makeText(LocationList.this, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    tv.setText(t.getMessage() + " failure");

                }
            });
        }
//        Call<List<PokemonForm>> listCall;
//
//            listCall = retrofitService.getPokemons();
//
//
//        listCall.enqueue(new Callback<List<PokemonForm>>() {
//            @Override
//            public void onResponse(Call<List<PokemonForm>> call, Response<List<PokemonForm>> response) {
//                if (!response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, response.code() + " not successful", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    List<PokemonForm> myList = response.body();
//                    Toast.makeText(MainActivity.this, ""+myList.get(0).getName(), Toast.LENGTH_SHORT).show();
//                    parseData(response.body());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<PokemonForm>> call, Throwable t) {
//                TextView tv = findViewById(R.id.error);
//                Toast.makeText(MainActivity.this, t.getMessage() + " failure", Toast.LENGTH_LONG).show();

//
//            }
//        });
    }

//    private void parseData(List<PokemonForm> body) {
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,body);
//        recyclerView.setAdapter(recyclerViewAdapter);
//
//    }


    private int detectGap(int quantity) {
        int finalId = currentId + quantity;
        if (finalId <= START_GAP || finalId > FINAL_GAP) {
            if (finalId > LIMIT_ID) {
                finalId = LIMIT_ID;
            }
            return finalId;
        }
        if (currentId > START_GAP) {
            currentId = FINAL_GAP;
            finalId = currentId + quantity;
            return finalId;
        }
        return START_GAP;

    }
}


