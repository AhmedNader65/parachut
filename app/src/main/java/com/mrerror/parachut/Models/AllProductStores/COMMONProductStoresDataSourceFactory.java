package com.mrerror.parachut.Models.AllProductStores;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class COMMONProductStoresDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<COMMONProducStoresDataSource> userLiveDataSource = new MutableLiveData<>();

    String id_;
    public COMMONProductStoresDataSourceFactory(String id_) {
        this.id_=id_;
    }


    @Override
    public DataSource<Long, Datum> create() {

        COMMONProducStoresDataSource offersDataSource = new COMMONProducStoresDataSource(id_);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
