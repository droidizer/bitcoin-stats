package com.codechallenge.badrobot.bitcoinstats;

import android.app.Activity;
import android.app.Application;

import com.codechallenge.badrobot.bitcoinstats.di.AppModule;
import com.codechallenge.badrobot.bitcoinstats.di.Component;
import com.codechallenge.badrobot.bitcoinstats.di.DaggerComponent;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.ConnectionBuddyConfiguration;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.fabric.sdk.android.Fabric;


public class BitcoinStatsApplication extends Application implements HasActivityInjector {

    Component mComponent;

    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerComponent
                .builder()
                .application(this)
                .appModule(new AppModule(this))
                .build();
        mComponent.inject(this);
        ConnectionBuddy.getInstance().init(new ConnectionBuddyConfiguration.Builder(this).build());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }
}
