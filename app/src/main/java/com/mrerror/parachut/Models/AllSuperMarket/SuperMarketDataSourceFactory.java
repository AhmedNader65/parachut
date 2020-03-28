package com.mrerror.parachut.Models.AllSuperMarket;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class SuperMarketDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<SuperMarketDataSource> userLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Long, Datum> create() {

        SuperMarketDataSource superMarketDataSource = new SuperMarketDataSource();
        userLiveDataSource.postValue(superMarketDataSource);
        return superMarketDataSource;
    }
}
