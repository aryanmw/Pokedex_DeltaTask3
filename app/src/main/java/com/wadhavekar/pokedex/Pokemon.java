package com.wadhavekar.pokedex;

import com.google.gson.annotations.SerializedName;
import com.wadhavekar.pokedex.PokemonStatistics.Statistics;
import com.wadhavekar.pokedex.TypesForMoreInformation.TypeForMoreInfo;

import java.util.List;

public class Pokemon {
    @SerializedName("forms")
    private List<PokemonForm> forms;
    @SerializedName("sprites")
    private PokemonSprite sprites;
    @SerializedName("base_experience")
    private String baseExperience;
    @SerializedName("species")
    private PokemonSpecies species;
    @SerializedName("abilities")
    private List<PokemonAbilities> ability;
    @SerializedName("stats")
    private List<Statistics> stats;
    @SerializedName("types")
    private List<TypeForMoreInfo> typeForMoreInfo;
    @SerializedName("id")
    private String pokemonId;

    public String getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(String pokemonId) {
        this.pokemonId = pokemonId;
    }

    public List<TypeForMoreInfo> getTypeForMoreInfo() {
        return typeForMoreInfo;
    }

    public void setTypeForMoreInfo(List<TypeForMoreInfo> typeForMoreInfo) {
        this.typeForMoreInfo = typeForMoreInfo;
    }

    public List<Statistics> getStats() {
        return stats;
    }

    public void setStats(List<Statistics> stats) {
        this.stats = stats;
    }

    public List<PokemonAbilities> getAbility() {
        return ability;
    }

    public void setAbility(List<PokemonAbilities> ability) {
        this.ability = ability;
    }

    public PokemonSpecies getSpecies() {
        return species;
    }

    public void setSpecies(PokemonSpecies species) {
        this.species = species;
    }

    public String getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(String baseExperience) {
        this.baseExperience = baseExperience;
    }

    public List<PokemonForm> getForms() {
        return forms;
    }

    public void setForms(List<PokemonForm> forms) {
        this.forms = forms;
    }

    public PokemonSprite getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprite sprites) {
        this.sprites = sprites;
    }

    public String getShortName() {
        String pokemonName = "";
        if (!this.forms.isEmpty()) {
            pokemonName = this.forms.get(0).getName();
        }
        return pokemonName;

    }
}
