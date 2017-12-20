package com.codechallenge.badrobot.bitcoinstats.di;

import com.codechallenge.badrobot.bitcoinstats.network.BitcoinNetworkManager;
import com.codechallenge.badrobot.bitcoinstats.network.IBitcoinNetworkManager;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ManagersModule {

    @Binds
    public abstract IBitcoinNetworkManager providesBitcoinNetworkManager(BitcoinNetworkManager facebookApiManager);

}
