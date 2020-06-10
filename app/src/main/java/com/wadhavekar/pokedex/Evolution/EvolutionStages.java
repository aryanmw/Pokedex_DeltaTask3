package com.wadhavekar.pokedex.Evolution;

import com.google.gson.annotations.SerializedName;

public class EvolutionStages {
    @SerializedName("name")
    private String stageName;

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}
