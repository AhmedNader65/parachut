package com.mrerror.parachut.Models.AllProductCategories;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class COMMONProductCategoryDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<COMMONProductCategoryDataSource> userLiveDataSource = new MutableLiveData<>();

    String id_;
    public COMMONProductCategoryDataSourceFactory(String id_) {
        this.id_=id_;
    }


    @Override
    public DataSource<Long, Datum> create() {

        COMMONProductCategoryDataSource offersDataSource = new COMMONProductCategoryDataSource(id_);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
