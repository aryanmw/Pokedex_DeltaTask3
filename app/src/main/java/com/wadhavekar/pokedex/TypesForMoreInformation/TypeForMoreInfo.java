package com.wadhavekar.pokedex.TypesForMoreInformation;

import com.google.gson.annotations.SerializedName;

public class TypeForMoreInfo {
    @SerializedName("type")
    private TypeName typeName;

    public TypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }
}
