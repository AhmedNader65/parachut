package com.mrerror.parachut.Models.CategoryModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class CategoryDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<CategoryDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public CategoryDataSourceFactory(Context context) {
        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        CategoryDataSource categoryDataSource = new CategoryDataSource(context);
        userLiveDataSource.postValue(categoryDataSource);
        return categoryDataSource;
    }
}
