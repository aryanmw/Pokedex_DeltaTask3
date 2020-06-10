package com.wadhavekar.pokedex.Evolution;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EvolutionChain {
    @SerializedName("species")
    List<EvolutionStages> evolutionStages;

    public List<EvolutionStages> getEvolutionStages() {
        return evolutionStages;
    }

    public void setEvolutionStages(List<EvolutionStages> evolutionStages) {
        this.evolutionStages = evolutionStages;
    }
}
