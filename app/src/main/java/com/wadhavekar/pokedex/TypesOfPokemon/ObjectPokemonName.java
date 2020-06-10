package com.wadhavekar.pokedex.TypesOfPokemon;

import com.google.gson.annotations.SerializedName;

public class ObjectPokemonName {
    @SerializedName("name")
    private String typePokemonName;

    public String getTypePokemonName() {
        return typePokemonName;
    }

    public void setTypePokemonName(String typePokemonName) {
        this.typePokemonName = typePokemonName;
    }
}
