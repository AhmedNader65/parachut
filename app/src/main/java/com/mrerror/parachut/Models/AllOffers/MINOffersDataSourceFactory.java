package com.mrerror.parachut.Models.AllOffers;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class MINOffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MINOffersDataSource> userLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Long, Datum> create() {

        MINOffersDataSource offersDataSource = new MINOffersDataSource();
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
