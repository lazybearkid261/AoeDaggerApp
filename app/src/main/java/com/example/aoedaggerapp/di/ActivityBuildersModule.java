package com.example.aoedaggerapp.di;

import com.example.aoedaggerapp.di.auth.AuthModule;
import com.example.aoedaggerapp.di.main.MainModule;
import com.example.aoedaggerapp.di.register.RegisterModule;
import com.example.aoedaggerapp.ui.auth.AuthActivity;
import com.example.aoedaggerapp.ui.main.MainActivity;
import com.example.aoedaggerapp.ui.register.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
            modules = { MainModule.class }
    )
    abstract MainActivity contributeMainActivity();


    @ContributesAndroidInjector(
            modules = {AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();


    @ContributesAndroidInjector(
            modules = {RegisterModule.class}
    )
    abstract RegisterActivity contributeRegisterActivity();
}
