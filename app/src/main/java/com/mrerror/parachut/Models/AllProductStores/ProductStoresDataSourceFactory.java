package com.mrerror.parachut.Models.AllProductStores;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class ProductStoresDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<ProductStoresDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    String id__;
    public ProductStoresDataSourceFactory(String id_, Context context) {
    id__=id_;
    this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        ProductStoresDataSource offersDataSource = new ProductStoresDataSource(id__,context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
