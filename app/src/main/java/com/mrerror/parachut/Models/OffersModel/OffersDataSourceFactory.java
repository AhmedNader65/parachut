package com.mrerror.parachut.Models.OffersModel;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class OffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<OffersDataSource> userLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Long, Datum> create() {

        OffersDataSource offersDataSource = new OffersDataSource();
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
