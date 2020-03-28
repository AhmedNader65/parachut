package com.mrerror.parachut.ui.home.homefragment.categoryfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.CategoryModel.CategoryDataSource;
import com.mrerror.parachut.Models.CategoryModel.CategoryDataSourceFactory;
import com.mrerror.parachut.Models.CategoryModel.Datum;

public class CategoryViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public LiveData<PagedList<Datum>> mutableLiveDataCategoryPageList;
    MutableLiveData<CategoryDataSource> categoryDataSourceMutableLiveData;
    public CategoryViewModel() {
        init();
        initAll();
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

    public LiveData<PagedList<com.mrerror.parachut.Models.AllCattegoryModel.Datum>> mutableLiveDataAllCategoryPageList;
    MutableLiveData<com.mrerror.parachut.Models.AllCattegoryModel.CategoryDataSource> allcategoryDataSourceMutableLiveData;


    private void initAll() {
        com.mrerror.parachut.Models.AllCattegoryModel.CategoryDataSourceFactory itemDataSourceFactory = new com.mrerror.parachut.Models.AllCattegoryModel.CategoryDataSourceFactory();
        allcategoryDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(com.mrerror.parachut.Models.AllCattegoryModel.CategoryDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataAllCategoryPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();

    }

}
