package com.codechallenge.badrobot.bitcoinstats.di;

import com.codechallenge.badrobot.bitcoinstats.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}
