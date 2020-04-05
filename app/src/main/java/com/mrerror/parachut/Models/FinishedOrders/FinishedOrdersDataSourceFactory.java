package com.mrerror.parachut.Models.FinishedOrders;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class FinishedOrdersDataSourceFactory extends DataSource.Factory<Long , com.mrerror.parachut.Models.FinishedOrders.Datum> {

    public MutableLiveData<FinishedOrdersDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public FinishedOrdersDataSourceFactory(Context context) {
    this.context=context;
    }

    @Override
    public DataSource<Long, Datum> create() {
        FinishedOrdersDataSource finishedOrdersDataSource = new FinishedOrdersDataSource(context);
        userLiveDataSource.postValue(finishedOrdersDataSource);
        return finishedOrdersDataSource;
    }
}
