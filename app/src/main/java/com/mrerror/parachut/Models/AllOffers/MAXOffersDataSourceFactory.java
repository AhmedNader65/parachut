package com.mrerror.parachut.Models.AllOffers;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class MAXOffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MAXOffersDataSource> userLiveDataSource = new MutableLiveData<>();
Context context;
    public MAXOffersDataSourceFactory(Context context) {
        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        MAXOffersDataSource offersDataSource = new MAXOffersDataSource(context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
