package com.mrerror.parachut.Models.AllProductCategories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class ProductCategoryDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<ProductCategoryDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    String id__;
    public ProductCategoryDataSourceFactory(String id_, Context context) {
    id__=id_;
    this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        ProductCategoryDataSource offersDataSource = new ProductCategoryDataSource(id__,context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
