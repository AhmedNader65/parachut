package com.mrerror.parachut.ui.home.homefragment;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.CategoryModel.CategoryDataSource;
import com.mrerror.parachut.Models.CategoryModel.CategoryDataSourceFactory;
import com.mrerror.parachut.Models.CategoryModel.Datum;
import com.mrerror.parachut.Models.OffersModel.OffersDataSource;
import com.mrerror.parachut.Models.OffersModel.OffersDataSourceFactory;
import com.mrerror.parachut.Models.SuperMarket.SuperMarketDataSource;
import com.mrerror.parachut.Models.SuperMarket.SuperMarketDataSourceFactory;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    Context context;

    public LiveData<PagedList<Datum>> mutableLiveDataCategoryPageList;
    MutableLiveData<CategoryDataSource> categoryDataSourceMutableLiveData;
    public MutableLiveData<Integer> progress = new MutableLiveData<>();



    public HomeViewModel(Context context) {
        this.context=context;
        init();
        init_sm();
        init_offers();

    }
    private void init() {
        CategoryDataSourceFactory itemDataSourceFactory = new CategoryDataSourceFactory(context);
        categoryDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(CategoryDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataCategoryPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();


    }




    public LiveData<PagedList<com.mrerror.parachut.Models.SuperMarket.Datum>> mutableLiveDataSuperMarketPageList;
    MutableLiveData<SuperMarketDataSource> superMarketDataSourceMutableLiveData;

    private void init_sm() {

        SuperMarketDataSourceFactory itemDataSourceFactory = new SuperMarketDataSourceFactory(context);
        superMarketDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(CategoryDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataSuperMarketPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }


    public LiveData<PagedList<com.mrerror.parachut.Models.Datum>> mutableLiveDataOffersPageList;
    MutableLiveData<OffersDataSource> ofeersDataSourceMutableLiveData;
    private void init_offers() {

        OffersDataSourceFactory itemDataSourceFactory = new OffersDataSourceFactory(context);
        ofeersDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(CategoryDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataOffersPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
        progress.setValue(View.GONE);

    }

}