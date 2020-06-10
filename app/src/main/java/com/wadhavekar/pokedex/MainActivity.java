package com.wadhavekar.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.wadhavekar.pokedex.DatabaseManager.DatabaseAdapter;
import com.wadhavekar.pokedex.FavouritePokemons.FavouritePokemonsDisplayActivity;
import com.wadhavekar.pokedex.Regions.RegionsActivity;
import com.wadhavekar.pokedex.TypesOfPokemon.TypesActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    int currentId = 1;
    private List<Pokemon> pokemons = new ArrayList<>();
    public static final int LIMIT_ID = 10147;
    public static final int START_GAP = 802;
    public static final int FINAL_GAP = 10001;
    RecyclerViewAdapter adapter;
    EditText searchFilter;
    TextView tv;
    DrawerLayout drawerLayout;
    ImageView search;
    NavigationView nv;
    Toolbar toolbar;
    RetrofitService retrofitService;
    DatabaseAdapter databaseAdapter;
    boolean isScrolling = false;
    private int currentItems,scrolledOutItems,totalItems;
    LinearLayoutManager manager;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new RecyclerViewAdapter(this, pokemons);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMain);
        manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        databaseAdapter = new DatabaseAdapter(this);
        progressBar = findViewById(R.id.progressBar);

        tv = findViewById(R.id.error);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        nv = findViewById(R.id.nav_view);
        searchFilter = findViewById(R.id.searchEditText);
        search = findViewById(R.id.iv_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFilter.setVisibility(View.VISIBLE);
            }
        });

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

        nv.bringToFront();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        getPokemonList();


        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String name = pokemons.get(viewHolder.getAdapterPosition()).getShortName();
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                databaseAdapter.addNameAndURL(pokemons.get(viewHolder.getAdapterPosition()).getShortName(),pokemons.get(viewHolder.getAdapterPosition()).getSprites().getFrontDefault());

            }
        };
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrolledOutItems = manager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrolledOutItems == totalItems)){
                    isScrolling = false;
                    getPokemonList();
                }
            }
        });


        nv.setNavigationItemSelectedListener(this);
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

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_region:
                //Toast.makeText(this, "Hey..Regions", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RegionsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_types:
                //Toast.makeText(this, "Hey..Types", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, TypesActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_fav:
                //Toast.makeText(this, "Hey..Types", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, FavouritePokemonsDisplayActivity.class);
                startActivity(intent2);
                break;

        }
        return true;
    }

    private void filter(String text) {
        ArrayList<Pokemon> filteredPokemonList = new ArrayList<>();
        for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getShortName().toLowerCase().contains(text.toLowerCase()) || pokemons.get(i).getPokemonId().contains(text)) {
                filteredPokemonList.add(pokemons.get(i));

            }
        }
        adapter.filterList(filteredPokemonList);
    }

    private void getPokemonList() {
        progressBar.setVisibility(View.VISIBLE);
        int finalId = detectGap(20);
        for (; currentId <= finalId; currentId++) {
            retrofitService.getPokemons(currentId).enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    pokemons.add(response.body());
                    //Collections.sort(pokemons);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    tv.setText(t.getMessage() + " failure");


                }
            });
        }
    }

    private void getEvolutionChain(){

    }

}


