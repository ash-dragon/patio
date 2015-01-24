package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SiteModel implements Serializable {
    @SerializedName("data") ArrayList<Site> sites;
    @SerializedName("erro") String error;

    public ArrayList<Site> getSites() {
        return sites;
    }

    public void setSites(ArrayList<Site> sites) {
        this.sites = sites;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
