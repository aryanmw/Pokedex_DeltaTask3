package com.wadhavekar.pokedex.PokemonStatistics;

import com.google.gson.annotations.SerializedName;

public class StatName {
    @SerializedName("name")
    private String pokemonStatName;

    public String getPokemonStatName() {
        return pokemonStatName;
    }

    public void setPokemonStatName(String pokemonStatName) {
        this.pokemonStatName = pokemonStatName;
    }
}
