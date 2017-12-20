package com.codechallenge.badrobot.bitcoinstats.di;


import com.codechallenge.badrobot.bitcoinstats.BitcoinStatsApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@dagger.Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ManagersModule.class,
        NetworkModule.class,
        MainActivityModule.class})
public interface Component {

    void inject(BitcoinStatsApplication bitcoinStatsApplication);

    @dagger.Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(BitcoinStatsApplication application);

        Builder appModule(AppModule appModule);

        Component build();
    }
}
