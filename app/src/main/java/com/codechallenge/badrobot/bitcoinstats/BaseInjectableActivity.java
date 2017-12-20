package com.codechallenge.badrobot.bitcoinstats;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.codechallenge.badrobot.bitcoinstats.viewmodel.BaseViewModel;

import dagger.android.AndroidInjection;

public abstract class BaseInjectableActivity extends AppCompatActivity {

    private BaseViewModel mAndroidBaseViewModel;

    protected ViewDataBinding mViewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    public void setContentView(int layoutResID, int bindingVariable, BaseViewModel androidViewModel) {
        if (androidViewModel != null) {
            mAndroidBaseViewModel = androidViewModel;
            mViewDataBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
            mViewDataBinding.setVariable(bindingVariable, androidViewModel);
            getLifecycle().addObserver(androidViewModel);
            setContentView(mViewDataBinding.getRoot());
        } else {
            setContentView(layoutResID);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAndroidBaseViewModel != null) {
            getLifecycle().removeObserver(mAndroidBaseViewModel);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
