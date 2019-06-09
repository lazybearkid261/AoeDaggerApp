package com.example.aoedaggerapp.network;

import androidx.lifecycle.LiveData;

import com.example.aoedaggerapp.network.responses.PlayerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainApi {

    @GET("Players/{id}")
    Call<PlayerResponse> getPlayerById(@Path("id") String id);
}
