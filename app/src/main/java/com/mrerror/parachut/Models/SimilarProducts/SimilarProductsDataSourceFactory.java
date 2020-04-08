package com.mrerror.parachut.Models.SimilarProducts;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class SimilarProductsDataSourceFactory extends DataSource.Factory<Long , Datum> {
    public MutableLiveData<SimilarProductsDataSource> userLiveDataSource = new MutableLiveData<>();
    Context context;

    public SimilarProductsDataSourceFactory(Context context) {
        this.context = context;
    }

    @Override
    public DataSource<Long, Datum> create() {
        SimilarProductsDataSource similarProductsDataSource = new SimilarProductsDataSource(context);
        userLiveDataSource.postValue(similarProductsDataSource);
        return similarProductsDataSource;
    }
}
