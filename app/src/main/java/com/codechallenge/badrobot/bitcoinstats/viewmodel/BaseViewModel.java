package com.codechallenge.badrobot.bitcoinstats.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.codechallenge.badrobot.bitcoinstats.R;
import com.codechallenge.badrobot.bitcoinstats.utils.ClickItemWrapper;
import com.codechallenge.badrobot.bitcoinstats.utils.IClickListener;
import com.codechallenge.badrobot.bitcoinstats.utils.MessageWrapper;
import com.codechallenge.badrobot.bitcoinstats.utils.SingleLiveEvent;

import java.io.IOException;

import retrofit2.HttpException;

public class BaseViewModel extends AndroidViewModel implements Observable, LifecycleObserver, IClickListener {

    private transient PropertyChangeRegistry mCallbacks;

    private SingleLiveEvent<MessageWrapper> mErrorMessageNotifier;

    private SingleLiveEvent<ClickItemWrapper> mItemClickNotifier;

    public BaseViewModel(Application application) {
        super(application);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {}

    public SingleLiveEvent<MessageWrapper> getErrorMessageNotifier() {
        return mErrorMessageNotifier;
    }

    protected void notifyMessage(int message) {
        notifyMessage(MessageWrapper.withSnackBar(message));
    }

    protected void notifyErrorMessage(Throwable throwable) {
        if (throwable instanceof HttpException || throwable instanceof IOException) {
            notifyMessage(R.string.connection_error);
        } else {
            notifyMessage(R.string.something_went_wrong);
        }
    }

    protected void notifyMessage(MessageWrapper message) {
        mErrorMessageNotifier.setValue(message);
    }

    @Override
    public SingleLiveEvent<ClickItemWrapper> getItemClickListenerNotifier() {
        return mItemClickNotifier;
    }
}
