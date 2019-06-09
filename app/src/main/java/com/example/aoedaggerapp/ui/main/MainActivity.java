package com.example.aoedaggerapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.Observer;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.example.aoedaggerapp.R;
import com.example.aoedaggerapp.models.entities.Players;
import com.example.aoedaggerapp.network.MainApi;
import com.example.aoedaggerapp.network.responses.PlayerResponse;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends DaggerAppCompatActivity {
    private static final String TAG = "MainActivity";
    @Inject
    MainApi mainApi;

    @Inject
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: " + mainApi + " UUID: " + firebaseAuth.getCurrentUser().getUid());

        Call<PlayerResponse> call = mainApi.getPlayerById("HEDSzm5QmyLW2tBSljnl");
        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: error" );
                } else {
                    Log.d(TAG, "onResponse: " + response.body().getPlayer().toString());
                    Log.d(TAG, "onResponse: " + response.body().getPlayer().getId().getStringValue());
                }
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }


}
