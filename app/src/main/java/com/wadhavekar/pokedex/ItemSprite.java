package com.wadhavekar.pokedex;

import com.google.gson.annotations.SerializedName;

public class ItemSprite {
    @SerializedName("default")
    private String itemUrl;

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
