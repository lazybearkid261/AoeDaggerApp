package com.example.aoedaggerapp.di.register;

import com.example.aoedaggerapp.network.RegisterApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RegisterModule {
    @Provides
    static RegisterApi provideRegisterApi(Retrofit retrofit){
        return retrofit.create(RegisterApi.class);
    }
}
