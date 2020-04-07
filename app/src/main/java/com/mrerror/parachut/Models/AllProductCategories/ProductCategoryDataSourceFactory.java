package com.mrerror.parachut.Models.AllProductCategories;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class ProductCategoryDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<ProductCategoryDataSource> userLiveDataSource = new MutableLiveData<>();

    String id__;
    public ProductCategoryDataSourceFactory(String id_) {
    id__=id_;
    }


    @Override
    public DataSource<Long, Datum> create() {

        ProductCategoryDataSource offersDataSource = new ProductCategoryDataSource(id__);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
