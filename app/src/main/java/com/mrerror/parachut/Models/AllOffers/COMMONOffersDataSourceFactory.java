package com.mrerror.parachut.Models.AllOffers;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class COMMONOffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<COMMONOffersDataSource> userLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Long, Datum> create() {

        COMMONOffersDataSource offersDataSource = new COMMONOffersDataSource();
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
