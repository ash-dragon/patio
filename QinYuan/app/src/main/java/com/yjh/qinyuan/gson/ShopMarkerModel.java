package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopMarkerModel implements Serializable {
    @SerializedName("data") ArrayList<ShopMarker> shopMarkers;
    @SerializedName("erro") String error;

    public ArrayList<ShopMarker> getShopMarkers() {
        return shopMarkers;
    }

    public void setShopMarkers(ArrayList<ShopMarker> shopMarkers) {
        this.shopMarkers = shopMarkers;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
