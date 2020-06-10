package com.wadhavekar.pokedex;

import com.google.gson.annotations.SerializedName;

public class Ability {
    @SerializedName("name")
    private  String abilityName;

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}
