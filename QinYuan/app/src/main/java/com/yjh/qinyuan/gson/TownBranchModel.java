package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ben on 1/24/15.
 */
public class TownBranchModel implements Serializable {
    @SerializedName("data") ArrayList<TownBranch> townBranches;
    @SerializedName("erro") String error;

    public ArrayList<TownBranch> getTownBranches() {
        return townBranches;
    }

    public void setTownBranches(ArrayList<TownBranch> townBranches) {
        this.townBranches = townBranches;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
