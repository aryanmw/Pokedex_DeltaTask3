package com.wadhavekar.pokedex.FavouritePokemons;

public class FavPokemon {
    String pokemonName;
    String imageURL;

    public FavPokemon(String pokemonName, String imageURL) {
        this.pokemonName = pokemonName;
        this.imageURL = imageURL;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public String getImageURL() {
        return imageURL;
    }
}
