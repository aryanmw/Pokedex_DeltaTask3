package com.wadhavekar.pokedex;

import com.wadhavekar.pokedex.Evolution.EvolutionChain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("pokemon/{id}/?limit=20&offset=20")
    Call<Pokemon> getPokemons(@Path("id") int id);

//    @GET("evolution-chain/{id}")
//    Call<EvolutionChain> getEvolutionChain(@Path("id") int id);



}
