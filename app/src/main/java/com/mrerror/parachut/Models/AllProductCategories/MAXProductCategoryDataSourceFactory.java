package com.mrerror.parachut.Models.AllProductCategories;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class MAXProductCategoryDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MAXProductCategoryDataSource> userLiveDataSource = new MutableLiveData<>();

    String id_;
    public MAXProductCategoryDataSourceFactory(String id_) {
    this.id_=id_;
    }


    @Override
    public DataSource<Long, Datum> create() {

        MAXProductCategoryDataSource offersDataSource = new MAXProductCategoryDataSource(id_);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
