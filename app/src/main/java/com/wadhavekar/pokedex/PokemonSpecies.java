package com.wadhavekar.pokedex;

import com.google.gson.annotations.SerializedName;

public class PokemonSpecies {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
