package com.wadhavekar.pokedex.TypesForMoreInformation;

import com.google.gson.annotations.SerializedName;

public class TypeName {
    @SerializedName("name")
    private String pokemonTypeName;

    public String getPokemonTypeName() {
        return pokemonTypeName;
    }

    public void setPokemonTypeName(String pokemonTypeName) {
        this.pokemonTypeName = pokemonTypeName;
    }
}
