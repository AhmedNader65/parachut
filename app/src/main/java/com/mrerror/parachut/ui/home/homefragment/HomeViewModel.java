package com.mrerror.parachut.ui.home.homefragment;

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

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public LiveData<PagedList<Datum>> mutableLiveDataCategoryPageList;
    MutableLiveData<CategoryDataSource> categoryDataSourceMutableLiveData;
    public HomeViewModel() {
        init();
        init_sm();
        init_offers();

    }
    private void init() {
        CategoryDataSourceFactory itemDataSourceFactory = new CategoryDataSourceFactory();
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

        SuperMarketDataSourceFactory itemDataSourceFactory = new SuperMarketDataSourceFactory();
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

        OffersDataSourceFactory itemDataSourceFactory = new OffersDataSourceFactory();
        ofeersDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(CategoryDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataOffersPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }

}