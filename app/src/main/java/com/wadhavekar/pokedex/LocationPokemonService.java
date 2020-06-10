package com.wadhavekar.pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationPokemonService {
    @GET("location/{id}")
    Call<LocationsOfPokemon> getPokemonLocation(@Path("id") int id);
}
