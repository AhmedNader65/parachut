package com.mrerror.parachut.Models.AllProductCategories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mrerror.parachut.Models.Datum;


public class COMMONProductCategoryDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<COMMONProductCategoryDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    String id_;
    public COMMONProductCategoryDataSourceFactory(String id_, Context context) {
        this.id_=id_;
        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        COMMONProductCategoryDataSource offersDataSource = new COMMONProductCategoryDataSource(id_,context);
        userLiveDataSource.postValue(offersDataSource);
        return offersDataSource;
    }
}
