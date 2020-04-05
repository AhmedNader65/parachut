package com.mrerror.parachut.Models.PendingOrders;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class PendingOrdersDataSourceFactory extends DataSource.Factory<Long, Datum> {
    public MutableLiveData<PendingOrdersDataSource> userLiveDataSource = new MutableLiveData<>();
    Context context;

    public PendingOrdersDataSourceFactory(Context context) {
        this.context = context;
    }

    @Override
    public DataSource<Long, Datum> create() {
        PendingOrdersDataSource pendingOrdersDataSource = new PendingOrdersDataSource(context);
        userLiveDataSource.postValue(pendingOrdersDataSource);
        return pendingOrdersDataSource;
    }
}
