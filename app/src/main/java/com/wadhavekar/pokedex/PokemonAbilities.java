package com.wadhavekar.pokedex;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonAbilities {
    @SerializedName("ability")
    Ability allAbilities;

    public Ability getAllAbilities() {
        return allAbilities;
    }

    public void setAllAbilities(Ability allAbilities) {
        this.allAbilities = allAbilities;
    }
//    public String getAbilityNames() {
//        String pokemonName = "";
//        if (!this.allAbilities.isEmpty()) {
//            pokemonName = this.allAbilities.get(0).getAbilityName();
//        }
//        return pokemonName;
//
//    }
}
