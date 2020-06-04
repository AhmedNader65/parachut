package com.mrerror.parachut.Models.AllProductStores;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class MINProductStoresDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MINProductStoresDataSource> userLiveDataSource = new MutableLiveData<>();

    String id_;
    Context context;
    public MINProductStoresDataSourceFactory(String id_, Context context) {
        this.id_=id_; this.context= context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        MINProductStoresDataSource offersDataSource = new MINProductStoresDataSource(id_,context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
