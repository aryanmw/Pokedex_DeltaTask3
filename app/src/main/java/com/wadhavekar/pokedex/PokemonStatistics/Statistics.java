package com.wadhavekar.pokedex.PokemonStatistics;

import com.google.gson.annotations.SerializedName;

public class Statistics {
    @SerializedName("stat")
    private StatName statName;

    @SerializedName("base_stat")
    private String statValue;

    public String getStatValue() {
        return statValue;
    }

    public void setStatValue(String statValue) {
        this.statValue = statValue;
    }

    public StatName getStatName() {
        return statName;
    }

    public void setStatName(StatName statName) {
        this.statName = statName;
    }
}
