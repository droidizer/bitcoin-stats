package com.codechallenge.badrobot.bitcoinstats.utils;

import com.crashlytics.android.Crashlytics;

final public class ErrorHandler {

    private ErrorHandler() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void handleError(Throwable e) {
        try {
            e.printStackTrace();
            Crashlytics.logException(e);
        } catch (Exception exception) {
            // Do nothing
        }
    }
}