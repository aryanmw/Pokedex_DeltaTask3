package com.wadhavekar.pokedex;

import com.google.gson.annotations.SerializedName;

public class PokemonForm {
    @SerializedName("name")
    String name;

//    @SerializedName("url")
//    String url;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    //    public String getThumbnailUrl() {
//        return thumbnailUrl;
//    }
//
//    public void setThumbnailUrl(String thumbnailUrl) {
//        this.thumbnailUrl = thumbnailUrl;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
}
