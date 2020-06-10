package com.wadhavekar.pokedex.Regions;

import com.wadhavekar.pokedex.TypesOfPokemon.TypesSerialization;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RegionsService {
    @GET("region/{id}")
    Call<RegionSerialization> getRegions(@Path("id") int id);
}
