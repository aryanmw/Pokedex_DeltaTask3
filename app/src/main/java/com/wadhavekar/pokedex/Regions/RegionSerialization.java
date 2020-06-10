package com.wadhavekar.pokedex.Regions;

import com.google.gson.annotations.SerializedName;
import com.wadhavekar.pokedex.TypesOfPokemon.PokemonNames;

import java.util.List;

public class RegionSerialization {
    @SerializedName("name")
    private String regionName;

    @SerializedName("locations")
    private List<LocationsInRegion> pokemonLocations;

    @SerializedName("id")
    private String regionId;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<LocationsInRegion> getPokemonLocations() {
        return pokemonLocations;
    }

    public void setPokemonNames(List<LocationsInRegion> pokemonNames) {
        this.pokemonLocations = pokemonNames;
    }
}
