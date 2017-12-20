package com.codechallenge.badrobot.bitcoinstats.network;


import com.codechallenge.badrobot.bitcoinstats.model.BitcoinMarketPriceModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BitcoinApiService {

    @GET("market-price?")
    Observable<BitcoinMarketPriceModel> getLast7DaysPrice(@Query("timespan") String period);
}


