package com.codechallenge.badrobot.bitcoinstats.viewmodel;


import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Bindable;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.codechallenge.badrobot.bitcoinstats.BR;
import com.codechallenge.badrobot.bitcoinstats.model.DataPoints;
import com.codechallenge.badrobot.bitcoinstats.network.IBitcoinNetworkManager;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class BitcoinMarketPriceViewModel extends BaseViewModel {

    private IBitcoinNetworkManager mBitcoinManager;

    private Disposable mDisposable = Disposables.disposed();
    private LineDataSet lineDataSet;
    private LineData lineData;

    @Inject
    BitcoinMarketPriceViewModel(Application application,
                                IBitcoinNetworkManager bitcoinManager) {
        super(application);
        mBitcoinManager = bitcoinManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDisposable = mBitcoinManager.getPrice("7days")
                .subscribe(this::notifyChanges, this::notifyErrorMessage);
    }

    private void notifyChanges(List<DataPoints> dataPoints) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < dataPoints.size(); i++) {
            entries.add(new Entry(dataPoints.get(i).getxPoint(), Float.parseFloat(String.valueOf(dataPoints.get(i).getyPoint()))));
        }

        Collections.sort(entries, new EntryXComparator());

        lineDataSet.setColor(Color.GREEN);
        lineDataSet.setValueTextColor(Color.RED);
        lineDataSet.setLineWidth(1f);

        lineData = new LineData(lineDataSet);
        notifyPropertyChanged(BR.dataPoints);
    }

    @Bindable
    public LineData getDataPoints() {
        return lineData;
    }

    public static class Factory extends ViewModelProviders.DefaultFactory {
        private final IBitcoinNetworkManager mBitcoinManager;

        public Factory(@NonNull Application application, IBitcoinNetworkManager bitcoinNetworkManager) {
            super(application);
            mBitcoinManager = bitcoinNetworkManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return super.create(modelClass);
        }
    }
}