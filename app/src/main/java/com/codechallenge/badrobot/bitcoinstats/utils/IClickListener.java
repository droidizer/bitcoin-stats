package com.codechallenge.badrobot.bitcoinstats.utils;


public interface IClickListener {

    SingleLiveEvent<ClickItemWrapper> getItemClickListenerNotifier();
}
