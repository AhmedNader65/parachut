package com.mrerror.parachut.ui.home.allitem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.AllOffers.COMMONOffersDataSource;
import com.mrerror.parachut.Models.AllOffers.COMMONOffersDataSourceFactory;
import com.mrerror.parachut.Models.AllOffers.Datum;
import com.mrerror.parachut.Models.AllOffers.MAXOffersDataSource;
import com.mrerror.parachut.Models.AllOffers.MAXOffersDataSourceFactory;
import com.mrerror.parachut.Models.AllOffers.MINOffersDataSource;
import com.mrerror.parachut.Models.AllOffers.MINOffersDataSourceFactory;
import com.mrerror.parachut.Models.AllOffers.OffersDataSource;
import com.mrerror.parachut.Models.AllOffers.OffersDataSourceFactory;
import com.mrerror.parachut.Models.AllSuperMarket.SuperMarketDataSource;
import com.mrerror.parachut.Models.AllSuperMarket.SuperMarketDataSourceFactory;

import static com.mrerror.parachut.Models.AllOffers.OffersDataSource.PAGE_SIZE;

public class AllitemViewModel extends ViewModel {



    public LiveData<PagedList<Datum>> mutableLiveDataOffersPageListMAX;
    public LiveData<PagedList<Datum>> mutableLiveDataOffersPageListMIN;
    public LiveData<PagedList<Datum>> mutableLiveDataOffersPageListCOMMON;

    public LiveData<PagedList<Datum>> mutableLiveDataOffersPageList;
    MutableLiveData<OffersDataSource> ofeersDataSourceMutableLiveData;
    MutableLiveData<MAXOffersDataSource> maxofeersDataSourceMutableLiveData;
    MutableLiveData<MINOffersDataSource> minofeersDataSourceMutableLiveData;
    MutableLiveData<COMMONOffersDataSource> commonofeersDataSourceMutableLiveData;

    public AllitemViewModel() {
        checkHowToSetFilterType(100);
        init_sm();
    }

    public void checkHowToSetFilterType(int i)
    {
        if(i==1){
            MAX_offers();
        }else if (i==2){
            COMMON_offers();
        }else if (i==3){
            MIN_offers();
        }
        else{
            init_offers();
        }
    }

    public void init_offers() {

        OffersDataSourceFactory itemDataSourceFactory = new OffersDataSourceFactory();
        ofeersDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataOffersPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void MAX_offers() {

        MAXOffersDataSourceFactory itemDataSourceFactory = new MAXOffersDataSourceFactory();
        maxofeersDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataOffersPageListMAX = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void MIN_offers() {

        MINOffersDataSourceFactory itemDataSourceFactory = new MINOffersDataSourceFactory();
        minofeersDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataOffersPageListMIN = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void COMMON_offers() {

        COMMONOffersDataSourceFactory itemDataSourceFactory = new COMMONOffersDataSourceFactory();
        commonofeersDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataOffersPageListCOMMON = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
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
