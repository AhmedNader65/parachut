package com.mrerror.parachut.Models.AllCattegoryModel;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class CategoryDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<CategoryDataSource> userLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Long, Datum> create() {

        CategoryDataSource categoryDataSource = new CategoryDataSource();
        userLiveDataSource.postValue(categoryDataSource);
        return categoryDataSource;
    }
}
