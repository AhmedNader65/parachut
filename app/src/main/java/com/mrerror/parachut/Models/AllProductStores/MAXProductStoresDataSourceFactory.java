package com.mrerror.parachut.Models.AllProductStores;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class MAXProductStoresDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MAXProductStoresDataSource> userLiveDataSource = new MutableLiveData<>();

    String id_;
    public MAXProductStoresDataSourceFactory(String id_) {
    this.id_=id_;
    }


    @Override
    public DataSource<Long, Datum> create() {

        MAXProductStoresDataSource offersDataSource = new MAXProductStoresDataSource(id_);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
