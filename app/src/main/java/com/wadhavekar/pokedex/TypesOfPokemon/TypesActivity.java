package com.wadhavekar.pokedex.TypesOfPokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wadhavekar.pokedex.LocationPokemonService;
import com.wadhavekar.pokedex.LocationRecyclerViewAdapter;
import com.wadhavekar.pokedex.LocationsOfPokemon;
import com.wadhavekar.pokedex.Pokemon;
import com.wadhavekar.pokedex.R;
import com.wadhavekar.pokedex.Regions.RegionSerialization;
import com.wadhavekar.pokedex.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText searchFilter;
    int currentId = 1;
    private List<TypesSerialization> pokemons = new ArrayList<>();
    public static final int LIMIT_ID = 10147;
    public static final int START_GAP = 802;
    public static final int FINAL_GAP = 10001;
    TypesRecyclerViewAdapter adapter;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TypesRecyclerViewAdapter(this,pokemons);
        setContentView(R.layout.activity_types);
        recyclerView = findViewById(R.id.rvMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(TypesActivity.this));
        recyclerView.setAdapter(adapter);
        tv = findViewById(R.id.error);

        searchFilter = findViewById(R.id.searchEditText);

        searchFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });




        TypesOfPokemonService retrofitService = RetrofitInstance.getRetrofitInstance().create(TypesOfPokemonService.class);
        int finalId = detectGap(18);
        for(;currentId < finalId ; currentId++){
            retrofitService.getPokemonTypes(currentId).enqueue(new Callback<TypesSerialization>() {
                @Override
                public void onResponse(Call<TypesSerialization> call, Response<TypesSerialization> response) {
                    pokemons.add(response.body());
                    //Collections.sort(pokemons);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<TypesSerialization> call, Throwable t) {
                    Toast.makeText(TypesActivity.this, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void filter(String text) {
        ArrayList<TypesSerialization> filteredPokemonList = new ArrayList<>();
        for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getTypeId().contains(text) || pokemons.get(i).getTypeName().toLowerCase().contains(text.toLowerCase())) {
                filteredPokemonList.add(pokemons.get(i));

            }
        }
        if (filteredPokemonList.size() == 0){
            TypesSerialization rs  = new TypesSerialization();
            rs.setTypeName("No object matches your search parameter");
            filteredPokemonList.add(rs);
        }
        adapter.filterList(filteredPokemonList);
    }
}
