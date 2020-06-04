package com.mrerror.parachut.Models.AllOffers;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class MINOffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MINOffersDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public MINOffersDataSourceFactory(Context context) {
        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        MINOffersDataSource offersDataSource = new MINOffersDataSource(context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
