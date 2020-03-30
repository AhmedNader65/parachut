package com.mrerror.parachut.ui.home.humbmenu.pricing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.Pricing.Datum;
import com.mrerror.parachut.Models.Pricing.PricingDataSource;
import com.mrerror.parachut.Models.Pricing.PricingDataSourceFactory;

public class PricingViewModel extends ViewModel {

    public LiveData<PagedList<Datum>> mutableLiveDataPricePageList;
    MutableLiveData<PricingDataSource> priceDataSourceMutableLiveData;


    public PricingViewModel() {
        init();
        initAll();
    }

    private void init() {
        PricingDataSourceFactory pricingDataSourceFactory = new PricingDataSourceFactory();
        priceDataSourceMutableLiveData = pricingDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PricingDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataPricePageList = new LivePagedListBuilder<>(pricingDataSourceFactory, config).build();

    }

    public LiveData<PagedList<com.mrerror.parachut.Models.Pricing.Datum>> mutableLiveDataPricingPageList;
    MutableLiveData<com.mrerror.parachut.Models.Pricing.PricingDataSource> allPricesDataSourceMutableLiveData;


    private void initAll() {
        com.mrerror.parachut.Models.Pricing.PricingDataSourceFactory itemPriceSourceFactory = new com.mrerror.parachut.Models.Pricing.PricingDataSourceFactory();
        allPricesDataSourceMutableLiveData = itemPriceSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(com.mrerror.parachut.Models.AllCattegoryModel.CategoryDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataPricingPageList = new LivePagedListBuilder<>(itemPriceSourceFactory, config).build();

    }

}
