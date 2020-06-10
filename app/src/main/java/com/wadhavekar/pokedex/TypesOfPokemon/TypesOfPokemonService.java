package com.wadhavekar.pokedex.TypesOfPokemon;

import com.wadhavekar.pokedex.LocationsOfPokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TypesOfPokemonService {
    @GET("type/{id}")
    Call<TypesSerialization> getPokemonTypes(@Path("id") int id);
}
