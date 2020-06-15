package com.mrerror.parachut.Models.Pricing;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PricingDataSource extends PageKeyedDataSource<Long , Datum> {
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Datum> callback) {
        RetroWeb.getClient().create(ServiceApi.class).onGetPriceModel(FIRST_PAGE).enqueue(new Callback<PriceModel>() {
            @Override
            public void onResponse(Call<PriceModel> call, Response<PriceModel> response) {

                PriceModel body = response.body();
                if (body != null) {
                    List<com.mrerror.parachut.Models.Pricing.Datum> data = body.getData();
                    callback.onResult(data, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<PriceModel> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
        RetroWeb.getClient().create(ServiceApi.class).onGetPriceModel(params.key).enqueue(new Callback<PriceModel>() {
            @Override
            public void onResponse(Call<PriceModel> call, Response<PriceModel> response) {
                PriceModel body = response.body();
                if (body != null) {
                    List<com.mrerror.parachut.Models.Pricing.Datum> data = body.getData();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(data, key);
                }

            }

            @Override
            public void onFailure(Call<PriceModel> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetPriceModel(params.key).enqueue(new Callback<PriceModel>() {
            @Override
            public void onResponse(Call<PriceModel> call, Response<PriceModel> response) {

                PriceModel body = response.body();
                if (body != null) {
                    List<com.mrerror.parachut.Models.Pricing.Datum> data = body.getData();
                    callback.onResult(data, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<PriceModel> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }
}
