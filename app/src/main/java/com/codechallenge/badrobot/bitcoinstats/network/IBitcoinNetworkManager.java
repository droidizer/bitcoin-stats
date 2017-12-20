package com.codechallenge.badrobot.bitcoinstats.network;


import com.codechallenge.badrobot.bitcoinstats.model.DataPoints;

import java.util.List;

import io.reactivex.Observable;

public interface IBitcoinNetworkManager {

    Observable<List<DataPoints>> getPrice(String period);
}
