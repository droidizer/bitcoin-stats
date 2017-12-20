package com.codechallenge.badrobot.bitcoinstats.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codechallenge.badrobot.bitcoinstats.BR;
import com.codechallenge.badrobot.bitcoinstats.BaseInjectableActivity;
import com.codechallenge.badrobot.bitcoinstats.R;
import com.codechallenge.badrobot.bitcoinstats.viewmodel.BitcoinMarketPriceViewModel;
import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends BaseInjectableActivity {

    @Inject
    BitcoinMarketPriceViewModel mBitcoinViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, BR.bitcoinStatsViewModel, mBitcoinViewModel);
        getLifecycle().addObserver(mBitcoinViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
