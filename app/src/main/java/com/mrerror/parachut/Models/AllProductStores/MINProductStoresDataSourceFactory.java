package com.mrerror.parachut.Models.AllProductStores;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class MINProductStoresDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MINProductStoresDataSource> userLiveDataSource = new MutableLiveData<>();

    String id_;
    public MINProductStoresDataSourceFactory(String id_) {
        this.id_=id_;
    }


    @Override
    public DataSource<Long, Datum> create() {

        MINProductStoresDataSource offersDataSource = new MINProductStoresDataSource(id_);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
