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

public class ListOfItems extends AppCompatActivity {
    RecyclerView recyclerView;
    int currentId = 1;
    private List<ItemsOfPokemon> pokemons = new ArrayList<>();
    public static final int LIMIT_ID = 10147;
    public static final int START_GAP = 802;
    public static final int FINAL_GAP = 10001;
    ItemRecyclerViewAdapter adapter;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemRecyclerViewAdapter(this,pokemons);
        setContentView(R.layout.activity_list_of_items);
        recyclerView = findViewById(R.id.rvMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListOfItems.this));
        recyclerView.setAdapter(adapter);
        tv = findViewById(R.id.error);



        PokemonItemsService retrofitService = RetrofitInstance.getRetrofitInstance().create(PokemonItemsService.class);
        int finalId = detectGap(10);
        for(;currentId <= finalId ; currentId++){
            retrofitService.getPokemonItems(currentId).enqueue(new Callback<ItemsOfPokemon>() {
                @Override
                public void onResponse(Call<ItemsOfPokemon> call, Response<ItemsOfPokemon> response) {
                    pokemons.add(response.body());
                    //Collections.sort(pokemons);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ItemsOfPokemon> call, Throwable t) {
                    Toast.makeText(ListOfItems.this, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
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

