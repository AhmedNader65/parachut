package com.mrerror.parachut.ui.home.orders.allorder;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.PendingOrders.Datum;
import com.mrerror.parachut.Models.PendingOrders.PendingOrdersDataSource;
import com.mrerror.parachut.Models.PendingOrders.PendingOrdersDataSourceFactory;

public class AllorderViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public LiveData<PagedList<Datum>> mutableLiveDataAllOrdersPageList;
    MutableLiveData<PendingOrdersDataSource> pendingOrdersDataSourceMutableLiveData;


    public LiveData<PagedList<Datum>> getMutableLiveDataOrdersPageList;
    MutableLiveData<PendingOrdersDataSource> allpendingOrdersDataSourceMutableLiveData;


    public void init(Context context) {
        PendingOrdersDataSourceFactory pendingOrdersDataSourceFactory = new PendingOrdersDataSourceFactory(context);
        pendingOrdersDataSourceMutableLiveData = pendingOrdersDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PendingOrdersDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataAllOrdersPageList = new LivePagedListBuilder<>(pendingOrdersDataSourceFactory, config).build();

    }

    public void initAll(Context context) {
        PendingOrdersDataSourceFactory itemOrdersSourceFactory = new PendingOrdersDataSourceFactory(context);
        allpendingOrdersDataSourceMutableLiveData = itemOrdersSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PendingOrdersDataSource.PAGE_SIZE)
                .build();
        getMutableLiveDataOrdersPageList = new LivePagedListBuilder<>(itemOrdersSourceFactory, config).build();

    }

}
