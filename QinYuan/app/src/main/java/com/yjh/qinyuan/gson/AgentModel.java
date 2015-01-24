package com.yjh.qinyuan.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AgentModel implements Serializable {
    @SerializedName("data") ArrayList<Agent> agents;
    @SerializedName("erro") String error;

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
