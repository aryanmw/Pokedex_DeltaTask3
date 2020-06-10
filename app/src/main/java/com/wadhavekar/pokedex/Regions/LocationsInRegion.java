package com.wadhavekar.pokedex.Regions;

import com.google.gson.annotations.SerializedName;

public class LocationsInRegion {
    @SerializedName("name")
    private String locationName;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
