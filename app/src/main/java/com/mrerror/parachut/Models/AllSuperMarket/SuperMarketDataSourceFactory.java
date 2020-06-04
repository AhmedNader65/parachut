package com.mrerror.parachut.Models.AllSuperMarket;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class SuperMarketDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<SuperMarketDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public SuperMarketDataSourceFactory(Context context) {
        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        SuperMarketDataSource superMarketDataSource = new SuperMarketDataSource(context);
        userLiveDataSource.postValue(superMarketDataSource);
        return superMarketDataSource;
    }
}
