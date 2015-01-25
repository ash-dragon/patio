package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShopMarker extends TownBranch implements Serializable {
    @SerializedName("longlen") double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
