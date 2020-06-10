package com.wadhavekar.pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonItemsService {
    @GET("item/{id}")
    Call<ItemsOfPokemon> getPokemonItems(@Path("id") int id);
}
