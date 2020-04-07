package com.mrerror.parachut.ui.home.allitem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mrerror.parachut.Models.AllOffers.COMMONOffersDataSource;
import com.mrerror.parachut.Models.AllOffers.COMMONOffersDataSourceFactory;
import com.mrerror.parachut.Models.AllOffers.MAXOffersDataSource;
import com.mrerror.parachut.Models.AllOffers.MAXOffersDataSourceFactory;
import com.mrerror.parachut.Models.AllOffers.MINOffersDataSource;
import com.mrerror.parachut.Models.AllOffers.MINOffersDataSourceFactory;
import com.mrerror.parachut.Models.AllOffers.OffersDataSource;
import com.mrerror.parachut.Models.AllOffers.OffersDataSourceFactory;
import com.mrerror.parachut.Models.AllProductCategories.COMMONProductCategoryDataSource;
import com.mrerror.parachut.Models.AllProductCategories.COMMONProductCategoryDataSourceFactory;
import com.mrerror.parachut.Models.AllProductCategories.MAXProductCategoryDataSource;
import com.mrerror.parachut.Models.AllProductCategories.MAXProductCategoryDataSourceFactory;
import com.mrerror.parachut.Models.AllProductCategories.MINProductCategoryDataSource;
import com.mrerror.parachut.Models.AllProductCategories.MINProductCategoryDataSourceFactory;
import com.mrerror.parachut.Models.AllProductCategories.ProductCategoryDataSource;
import com.mrerror.parachut.Models.AllProductCategories.ProductCategoryDataSourceFactory;
import com.mrerror.parachut.Models.AllProductStores.COMMONProducStoresDataSource;
import com.mrerror.parachut.Models.AllProductStores.COMMONProductStoresDataSourceFactory;
import com.mrerror.parachut.Models.AllProductStores.MAXProductStoresDataSource;
import com.mrerror.parachut.Models.AllProductStores.MAXProductStoresDataSourceFactory;
import com.mrerror.parachut.Models.AllProductStores.MINProductStoresDataSource;
import com.mrerror.parachut.Models.AllProductStores.MINProductStoresDataSourceFactory;
import com.mrerror.parachut.Models.AllProductStores.ProductStoresDataSource;
import com.mrerror.parachut.Models.AllProductStores.ProductStoresDataSourceFactory;
import com.mrerror.parachut.Models.AllSuperMarket.SuperMarketDataSource;
import com.mrerror.parachut.Models.AllSuperMarket.SuperMarketDataSourceFactory;
import com.mrerror.parachut.Models.Datum;

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
    
    
    //////////Product Category

    public void checkHowToSetFilterTypeProductCategory(int i,String ID_)
    {
        if(i==1){
            MAX_ProductCategory(ID_);
        }else if (i==2){
            COMMON_ProductCategory(ID_);
        }else if (i==3){
            MIN_ProductCategory(ID_);
        }
        else{
            init_ProductCategory(ID_);
        }
    }


    public LiveData<PagedList<Datum>> mutableLiveDataProductCategoryPageListMAX;
    public LiveData<PagedList<Datum>> mutableLiveDataProductCategoryPageListMIN;
    public LiveData<PagedList<Datum>> mutableLiveDataProductCategoryPageListCOMMON;
    public LiveData<PagedList<Datum>> mutableLiveDataProductCategoryPageList;

    MutableLiveData<ProductCategoryDataSource> ProductCategoryDataSourceMutableLiveData;
    MutableLiveData<MAXProductCategoryDataSource> maxProductCategoryDataSourceMutableLiveData;
    MutableLiveData<MINProductCategoryDataSource> minProductCategoryDataSourceMutableLiveData;
    MutableLiveData<COMMONProductCategoryDataSource> commonProductCategoryDataSourceMutableLiveData;
    
    public void init_ProductCategory(String ID_) {

        ProductCategoryDataSourceFactory itemDataSourceFactory = new ProductCategoryDataSourceFactory(ID_);
        ProductCategoryDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataProductCategoryPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void MAX_ProductCategory(String ID_) {

        MAXProductCategoryDataSourceFactory itemDataSourceFactory = new MAXProductCategoryDataSourceFactory(ID_);
        maxProductCategoryDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataProductCategoryPageListMAX = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void MIN_ProductCategory(String ID_) {

        MINProductCategoryDataSourceFactory itemDataSourceFactory = new MINProductCategoryDataSourceFactory(ID_);
        minProductCategoryDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataProductCategoryPageListMIN = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void COMMON_ProductCategory(String ID_) {

        COMMONProductCategoryDataSourceFactory itemDataSourceFactory = new COMMONProductCategoryDataSourceFactory(ID_);
        commonProductCategoryDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataProductCategoryPageListCOMMON = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    
  ////////////////////  
  public void checkHowToSetFilterTypeProductStores(int i,String ID_)
  {
      if(i==1){
          MAX_ProductStores(ID_);
      }else if (i==2){
          COMMON_ProductStores(ID_);
      }else if (i==3){
          MIN_ProductStores(ID_);
      }
      else{
          init_ProductStores(ID_);
      }
  }


    public LiveData<PagedList<Datum>> mutableLiveDataProductStoresPageListMAX;
    public LiveData<PagedList<Datum>> mutableLiveDataProductStoresPageListMIN;
    public LiveData<PagedList<Datum>> mutableLiveDataProductStoresPageListCOMMON;
    public LiveData<PagedList<Datum>> mutableLiveDataProductStoresPageList;

    MutableLiveData<ProductStoresDataSource> ProductStoresDataSourceMutableLiveData;
    MutableLiveData<MAXProductStoresDataSource> maxProductStoresDataSourceMutableLiveData;
    MutableLiveData<MINProductStoresDataSource> minProductStoresDataSourceMutableLiveData;
    MutableLiveData<COMMONProducStoresDataSource> commonProductStoresDataSourceMutableLiveData;

    public void init_ProductStores(String ID_) {

        ProductStoresDataSourceFactory itemDataSourceFactory = new ProductStoresDataSourceFactory(ID_);
        ProductStoresDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataProductStoresPageList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void MAX_ProductStores(String ID_) {

        MAXProductStoresDataSourceFactory itemDataSourceFactory = new MAXProductStoresDataSourceFactory(ID_);
        maxProductStoresDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataProductStoresPageListMAX = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void MIN_ProductStores(String ID_) {

        MINProductStoresDataSourceFactory itemDataSourceFactory = new MINProductStoresDataSourceFactory(ID_);
        minProductStoresDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataProductStoresPageListMIN = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
    public void COMMON_ProductStores(String ID_) {

        COMMONProductStoresDataSourceFactory itemDataSourceFactory = new COMMONProductStoresDataSourceFactory(ID_);
        commonProductStoresDataSourceMutableLiveData = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        mutableLiveDataProductStoresPageListCOMMON = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
}
