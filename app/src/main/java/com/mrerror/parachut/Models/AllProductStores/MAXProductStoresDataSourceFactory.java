package com.mrerror.parachut.Models.AllProductStores;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class MAXProductStoresDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MAXProductStoresDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    String id_;
    public MAXProductStoresDataSourceFactory(String id_, Context context) {
    this.id_=id_; this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        MAXProductStoresDataSource offersDataSource = new MAXProductStoresDataSource(id_,context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
