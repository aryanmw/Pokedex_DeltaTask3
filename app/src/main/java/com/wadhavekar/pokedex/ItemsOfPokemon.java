package com.wadhavekar.pokedex;

import com.google.gson.annotations.SerializedName;

public class ItemsOfPokemon {
    @SerializedName("name")
    private String itemName;

    @SerializedName("sprites")
    private ItemSprite itemSprites;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemSprite getItemSprites() {
        return itemSprites;
    }

    public void setItemSprites(ItemSprite itemSprites) {
        this.itemSprites = itemSprites;
    }
}
