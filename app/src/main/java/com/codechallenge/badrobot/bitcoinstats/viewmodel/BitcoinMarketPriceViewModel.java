package com.codechallenge.badrobot.bitcoinstats.viewmodel;


import android.app.Application;
import android.content.res.Resources;
import android.databinding.Bindable;
import android.graphics.Color;

import com.codechallenge.badrobot.bitcoinstats.BR;
import com.codechallenge.badrobot.bitcoinstats.R;
import com.codechallenge.badrobot.bitcoinstats.model.DataPoints;
import com.codechallenge.badrobot.bitcoinstats.network.IBitcoinNetworkManager;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class BitcoinMarketPriceViewModel extends BaseViewModel {

    private final Resources mResources;
    private IBitcoinNetworkManager mBitcoinManager;

    private Disposable mDisposable = Disposables.disposed();
    private LineDataSet lineDataSet;
    private LineData lineData;

    @Inject
    BitcoinMarketPriceViewModel(Application application,
                                Resources resources,
                                IBitcoinNetworkManager bitcoinManager) {
        super(application);
        mResources = resources;
        mBitcoinManager = bitcoinManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDisposable = mBitcoinManager.getPrice("30days")
                .subscribe(this::notifyChanges, this::notifyErrorMessage);
    }

    private void notifyChanges(List<DataPoints> dataPoints) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < dataPoints.size(); i++) {
            entries.add(new Entry(dataPoints.get(i).getxPoint(), Float.parseFloat(String.valueOf(dataPoints.get(i).getyPoint()))));
        }

        Collections.sort(entries, new EntryXComparator());

        lineDataSet = new LineDataSet(entries, mResources.getString(R.string.market_price));
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setValueTextSize(mResources.getDimension(R.dimen.text_2));
        lineDataSet.setValueTextColor(Color.RED);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setLineWidth(1f);

        lineData = new LineData(lineDataSet);
        notifyPropertyChanged(BR.dataPoints);
        notifyPropertyChanged(BR.formatterInt);
    }

    @Bindable
    public int getFormatterInt() {
        return 0;
    }

    @Bindable
    public LineData getDataPoints() {
        return lineData;
    }
}