package com.codechallenge.badrobot.bitcoinstats.utils;


import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MessageDelegate extends ViewModel {

    private Toast mToast;

    private Snackbar mSnackbar;

    private WeakReference<Activity> mActivity;

    public void setActivity(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    public void showMessage(MessageWrapper messageWrapper) {
        try {
            if (messageWrapper == null || mActivity == null || mActivity.get() == null) {
                return;
            }
            MessageWrapper.Type type = messageWrapper.getType();
            switch (type) {
                case TOAST:
                    if (messageWrapper.getMessageId() > 0) {
                        if (mToast != null) {
                            mToast.cancel();
                        }
                        mToast = Toast.makeText(mActivity.get(), messageWrapper.getMessageId(), messageWrapper.getDuration());
                        mToast.show();
                    }
                    break;

                case SNACKBAR:
                    if (messageWrapper.getMessageId() > 0) {
                        if (mSnackbar != null) {
                            mSnackbar.dismiss();
                        }
                        mSnackbar = Snackbar
                                .make(mActivity.get()
                                        .findViewById(android.R.id.content), messageWrapper.getMessageId(), messageWrapper.getDuration());
                        mSnackbar.show();
                    }
                    break;
            }
        } catch (Exception e) {
            ErrorHandler.handleError(e);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mActivity != null) {
            mActivity.clear();
            mActivity = null;
        }
        if (mToast != null) {
            mToast.cancel();
        }
        if (mSnackbar != null) {
            mSnackbar.dismiss();
        }
    }
}
