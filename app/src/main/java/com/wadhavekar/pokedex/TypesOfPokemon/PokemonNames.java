package com.wadhavekar.pokedex.TypesOfPokemon;

import com.google.gson.annotations.SerializedName;

public class PokemonNames {
    @SerializedName("pokemon")
    ObjectPokemonName objName;

    public ObjectPokemonName getObjName() {
        return objName;
    }

    public void setObjName(ObjectPokemonName objName) {
        this.objName = objName;
    }
}
