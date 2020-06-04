package com.mrerror.parachut.Models.AllOffers;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class COMMONOffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<COMMONOffersDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public COMMONOffersDataSourceFactory(Context context) {
        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        COMMONOffersDataSource offersDataSource = new COMMONOffersDataSource(context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
