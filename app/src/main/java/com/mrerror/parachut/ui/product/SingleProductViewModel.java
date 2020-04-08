package com.mrerror.parachut.ui.product;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.SimilarProducts.Datum;
import com.mrerror.parachut.Models.SimilarProducts.SimilarProductsDataSource;
import com.mrerror.parachut.Models.SimilarProducts.SimilarProductsDataSourceFactory;

public class SingleProductViewModel extends ViewModel {
    public LiveData<PagedList<com.mrerror.parachut.Models.SimilarProducts.Datum>> mutableLiveDataOrdersPageList;
    MutableLiveData<SimilarProductsDataSource> similarProductsDataSourceMutableLiveData;


    public SingleProductViewModel(Context context) {
        init(context);
        initAll(context);
    }

    private void init(Context context) {
        SimilarProductsDataSourceFactory similarProductsDataSourceFactory = new SimilarProductsDataSourceFactory(context);
        similarProductsDataSourceMutableLiveData = similarProductsDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(SimilarProductsDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataOrdersPageList = new LivePagedListBuilder<>(similarProductsDataSourceFactory, config).build();

    }

    public LiveData<PagedList<Datum>> getMutableLiveDataOrdersPageList;
    MutableLiveData<SimilarProductsDataSource> allsimilarProductsDataSourceMutableLiveData;


    private void initAll(Context context) {
        SimilarProductsDataSourceFactory itemOrdersSourceFactory = new SimilarProductsDataSourceFactory(context);
        allsimilarProductsDataSourceMutableLiveData = itemOrdersSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(SimilarProductsDataSource.PAGE_SIZE)
                .build();
        getMutableLiveDataOrdersPageList = new LivePagedListBuilder<>(itemOrdersSourceFactory, config).build();

    }

}
