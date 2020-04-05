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

    public AllorderViewModel(Context context) {
        init(context);
        initAll(context);
    }
    private void init(Context context) {
        PendingOrdersDataSourceFactory pendingOrdersDataSourceFactory = new PendingOrdersDataSourceFactory(context);
        pendingOrdersDataSourceMutableLiveData = pendingOrdersDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PendingOrdersDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataAllOrdersPageList = new LivePagedListBuilder<>(pendingOrdersDataSourceFactory, config).build();

    }

    public LiveData<PagedList<Datum>> getMutableLiveDataOrdersPageList;
    MutableLiveData<PendingOrdersDataSource> allpendingOrdersDataSourceMutableLiveData;


    private void initAll(Context context) {
        com.mrerror.parachut.Models.PendingOrders.PendingOrdersDataSourceFactory itemOrdersSourceFactory = new com.mrerror.parachut.Models.PendingOrders.PendingOrdersDataSourceFactory(context);
        allpendingOrdersDataSourceMutableLiveData = itemOrdersSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(com.mrerror.parachut.Models.PendingOrders.PendingOrdersDataSource.PAGE_SIZE)
                .build();
        getMutableLiveDataOrdersPageList = new LivePagedListBuilder<>(itemOrdersSourceFactory, config).build();

    }

}
