package com.wadhavekar.pokedex.TypesOfPokemon;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TypesSerialization {
    @SerializedName("name")
    private String typeName;

    @SerializedName("pokemon")
    private List<PokemonNames> pokemonNames;

    @SerializedName("id")
    private String typeId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public List<PokemonNames> getPokemonNames() {
        return pokemonNames;
    }

    public void setPokemonNames(List<PokemonNames> pokemonNames) {
        this.pokemonNames = pokemonNames;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
