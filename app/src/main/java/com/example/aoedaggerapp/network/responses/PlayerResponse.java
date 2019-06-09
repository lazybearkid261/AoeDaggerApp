package com.example.aoedaggerapp.network.responses;

import com.example.aoedaggerapp.models.entities.Players;
import com.google.gson.annotations.SerializedName;

public class PlayerResponse {

    private String name;

    @SerializedName("fields")
    private Players player;

    @SerializedName("createTime")
    private String createTime;

    @SerializedName("updateTime")
    private String updateTime;

    public String getName() {
        return name;
    }

    public Players getPlayer() {
        return player;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }
}
