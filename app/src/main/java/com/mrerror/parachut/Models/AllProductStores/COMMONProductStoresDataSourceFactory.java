package com.mrerror.parachut.Models.AllProductStores;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class COMMONProductStoresDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<COMMONProducStoresDataSource> userLiveDataSource = new MutableLiveData<>();

    String id_;
    Context context;
    public COMMONProductStoresDataSourceFactory(String id_,Context context) {
        this.id_=id_; this.context=context;

    }


    @Override
    public DataSource<Long, Datum> create() {

        COMMONProducStoresDataSource offersDataSource = new COMMONProducStoresDataSource(id_,context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
