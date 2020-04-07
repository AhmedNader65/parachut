package com.mrerror.parachut.Models.AllProductCategories;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class MINProductCategoryDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<MINProductCategoryDataSource> userLiveDataSource = new MutableLiveData<>();

    String id_;
    public MINProductCategoryDataSourceFactory(String id_) {
        this.id_=id_;
    }


    @Override
    public DataSource<Long, Datum> create() {

        MINProductCategoryDataSource offersDataSource = new MINProductCategoryDataSource(id_);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
