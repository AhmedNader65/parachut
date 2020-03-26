package com.mrerror.parachut.ui.home.homefragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.CategoryModel.CategoryDataSource;
import com.mrerror.parachut.Models.CategoryModel.CategoryDataSourceFactory;
import com.mrerror.parachut.Models.CategoryModel.Datum;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public LiveData<PagedList<Datum>> mutableLiveDataCategoryPageList;
    private MutableLiveData<CategoryDataSource> categoryDataSourceMutableLiveData;

    public HomeViewModel() {
        init();
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
}