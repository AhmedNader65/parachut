package com.mrerror.parachut.Models.OffersModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class OffersDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<OffersDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public OffersDataSourceFactory(Context context) {
        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        OffersDataSource offersDataSource = new OffersDataSource(context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
