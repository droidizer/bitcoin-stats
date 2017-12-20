package com.codechallenge.badrobot.bitcoinstats.network;


import com.codechallenge.badrobot.bitcoinstats.model.DataPoints;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Singleton
public class BitcoinNetworkManager implements IBitcoinNetworkManager {

    private final BitcoinApiService mBitcoinApiService;

    @Inject
    public BitcoinNetworkManager(BitcoinApiService bitcoinApiService) {
        mBitcoinApiService = bitcoinApiService;
    }

    @Override
    public Observable<List<DataPoints>> getPrice(String period) {
        return mBitcoinApiService.getLast7DaysPrice(period)
                .filter(responseBody -> responseBody != null)
                .flatMap(response -> {
                    if (response.datapoints != null && !response.datapoints.isEmpty()) {
                        return Observable.just(response.datapoints);
                    }

                    return Observable.empty();
                });
    }
}
