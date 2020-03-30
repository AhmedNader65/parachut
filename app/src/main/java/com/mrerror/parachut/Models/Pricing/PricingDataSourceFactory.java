package com.mrerror.parachut.Models.Pricing;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class PricingDataSourceFactory extends DataSource.Factory<Long, com.mrerror.parachut.Models.Pricing.Datum> {

    public MutableLiveData<PricingDataSource> userLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Long, com.mrerror.parachut.Models.Pricing.Datum> create() {
        PricingDataSource pricingDataSource = new PricingDataSource();
        userLiveDataSource.postValue(pricingDataSource);
        return pricingDataSource;
    }
}
