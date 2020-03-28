package com.mrerror.parachut.ui.home.allitem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.AllOffers.Datum;
import com.mrerror.parachut.Models.AllOffers.OffersDataSource;
import com.mrerror.parachut.Models.AllOffers.OffersDataSourceFactory;
import com.mrerror.parachut.Models.AllSuperMarket.SuperMarketDataSource;
import com.mrerror.parachut.Models.AllSuperMarket.SuperMarketDataSourceFactory;

import static com.mrerror.parachut.Models.AllOffers.OffersDataSource.PAGE_SIZE;

public class AllitemViewModel extends ViewModel {

    public LiveData<PagedList<Datum>> mutableLiveDataOffersPageList;
    MutableLiveData<OffersDataSource> ofeersDataSourceMutableLiveData;

    public AllitemViewModel() {
        init_offers();
        init_sm();
    }
    private void init_offers() {

        OffersDataSourceFactory itemDataSourceFactory = new OffersDataSourceFactory();
        ofeersDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataOffersPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }

    public LiveData<PagedList<com.mrerror.parachut.Models.AllSuperMarket.Datum>> mutableLiveDataSuperMarketPageList;
    MutableLiveData<SuperMarketDataSource> superMarketDataSourceMutableLiveData;

    private void init_sm() {

        SuperMarketDataSourceFactory itemDataSourceFactory = new SuperMarketDataSourceFactory();
        superMarketDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(com.mrerror.parachut.Models.AllSuperMarket.SuperMarketDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataSuperMarketPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
}
