package com.mrerror.parachut.Models.AllProductStores;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class ProductStoresDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<ProductStoresDataSource> userLiveDataSource = new MutableLiveData<>();

    String id__;
    public ProductStoresDataSourceFactory(String id_) {
    id__=id_;
    }


    @Override
    public DataSource<Long, Datum> create() {

        ProductStoresDataSource offersDataSource = new ProductStoresDataSource(id__);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
