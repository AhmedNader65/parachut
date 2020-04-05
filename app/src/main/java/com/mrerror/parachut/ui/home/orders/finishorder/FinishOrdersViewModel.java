package com.mrerror.parachut.ui.home.orders.finishorder;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.FinishedOrders.Datum;
import com.mrerror.parachut.Models.FinishedOrders.FinishedOrdersDataSource;
import com.mrerror.parachut.Models.FinishedOrders.FinishedOrdersDataSourceFactory;

public class FinishOrdersViewModel extends ViewModel {
    public LiveData<PagedList<com.mrerror.parachut.Models.FinishedOrders.Datum>> mutableLiveDataOrdersPageList;
    MutableLiveData<FinishedOrdersDataSource> finishedOrdersDataSourceMutableLiveData;


    public FinishOrdersViewModel(Context context) {
        init(context);
        initAll(context);
    }

    private void init(Context context) {
        FinishedOrdersDataSourceFactory finishedOrdersDataSourceFactory = new FinishedOrdersDataSourceFactory(context);
        finishedOrdersDataSourceMutableLiveData = finishedOrdersDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(FinishedOrdersDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataOrdersPageList = new LivePagedListBuilder<>(finishedOrdersDataSourceFactory, config).build();

    }

    public LiveData<PagedList<Datum>> getMutableLiveDataOrdersPageList;
    MutableLiveData<FinishedOrdersDataSource> allfinishedOrdersDataSourceMutableLiveData;


    private void initAll(Context context) {
        com.mrerror.parachut.Models.FinishedOrders.FinishedOrdersDataSourceFactory itemOrdersSourceFactory = new com.mrerror.parachut.Models.FinishedOrders.FinishedOrdersDataSourceFactory(context);
        allfinishedOrdersDataSourceMutableLiveData = itemOrdersSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(com.mrerror.parachut.Models.FinishedOrders.FinishedOrdersDataSource.PAGE_SIZE)
                .build();
        getMutableLiveDataOrdersPageList = new LivePagedListBuilder<>(itemOrdersSourceFactory, config).build();

    }

}
