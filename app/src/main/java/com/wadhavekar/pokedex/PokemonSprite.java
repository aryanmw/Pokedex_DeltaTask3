package com.wadhavekar.pokedex;

import com.google.gson.annotations.SerializedName;

public class PokemonSprite {
    @SerializedName("front_default")
    private String frontDefault;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}
