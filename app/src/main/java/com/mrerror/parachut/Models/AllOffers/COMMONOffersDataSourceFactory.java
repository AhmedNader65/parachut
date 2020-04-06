package com.mrerror.parachut.Models.AllOffers;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class COMMONOffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<COMMONOffersDataSource> userLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Long, Datum> create() {

        COMMONOffersDataSource offersDataSource = new COMMONOffersDataSource();
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
