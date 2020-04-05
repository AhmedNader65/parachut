package com.mrerror.parachut.Models.FinishedOrders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinishedOrdersDataSource extends PageKeyedDataSource<Long , Datum> {
    GlobalPrefrencies globalPrefrencies;
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;

    Context  context;
    public FinishedOrdersDataSource(Context context) {
    this.context=context;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Datum> callback) {

        globalPrefrencies = new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetFinishedOrders("Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<FinishedOrdersModel>() {
            @Override
            public void onResponse(Call<FinishedOrdersModel> call, Response<FinishedOrdersModel> response) {
                FinishedOrdersModel model = response.body();
                if (model!= null) {
                    List<Datum> data = model.getData();
                    callback.onResult(data , null , FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<FinishedOrdersModel> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Datum> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
        RetroWeb.getClient().create(ServiceApi.class).onGetFinishedOrders("Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<FinishedOrdersModel>() {
            @Override
            public void onResponse(Call<FinishedOrdersModel> call, Response<FinishedOrdersModel> response) {
                FinishedOrdersModel model = response.body();
                if (model != null) {
                    List<com.mrerror.parachut.Models.FinishedOrders.Datum> data = model.getData();
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
            public void onFailure(Call<FinishedOrdersModel> call, Throwable t) {

            }
        });
    }
}