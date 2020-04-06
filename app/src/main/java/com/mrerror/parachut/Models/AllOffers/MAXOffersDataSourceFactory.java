package com.mrerror.parachut.Models.AllOffers;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class MAXOffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MAXOffersDataSource> userLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Long, Datum> create() {

        MAXOffersDataSource offersDataSource = new MAXOffersDataSource();
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
