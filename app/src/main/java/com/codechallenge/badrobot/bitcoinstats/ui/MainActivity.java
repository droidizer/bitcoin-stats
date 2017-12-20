package com.codechallenge.badrobot.bitcoinstats.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codechallenge.badrobot.bitcoinstats.BR;
import com.codechallenge.badrobot.bitcoinstats.BaseInjectableActivity;
import com.codechallenge.badrobot.bitcoinstats.R;
import com.codechallenge.badrobot.bitcoinstats.viewmodel.BitcoinMarketPriceViewModel;
import com.github.mikephil.charting.charts.LineChart;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends BaseInjectableActivity {

    @Inject
    BitcoinMarketPriceViewModel mBitcoinViewModel;

    LineChart lineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, BR.bitcoinStatsViewModel, mBitcoinViewModel);
        getLifecycle().addObserver(mBitcoinViewModel);

        lineChart = findViewById(R.id.line_chart);
        lineChart.setScaleEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
