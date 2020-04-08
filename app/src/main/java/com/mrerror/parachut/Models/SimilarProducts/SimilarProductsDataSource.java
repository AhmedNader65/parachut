package com.mrerror.parachut.Models.SimilarProducts;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimilarProductsDataSource extends PageKeyedDataSource<Long , Datum> {
    GlobalPrefrencies globalPrefrencies;
    Context context;
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;

    public SimilarProductsDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Datum> callback) {
        globalPrefrencies = new GlobalPrefrencies(context);

        RetroWeb.getClient().create(ServiceApi.class).onGetSimilarProductsModel("Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<SimilarProductsModel>() {
            @Override
            public void onResponse(Call<SimilarProductsModel> call, Response<SimilarProductsModel> response) {
                SimilarProductsModel model = response.body();
                if (model != null) {
                    List<Datum> data = model.getData();
                    callback.onResult(data , null , FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<SimilarProductsModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
        RetroWeb.getClient().create(ServiceApi.class).onGetSimilarProductsModel("Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<SimilarProductsModel>() {
            @Override
            public void onResponse(Call<SimilarProductsModel> call, Response<SimilarProductsModel> response) {
                SimilarProductsModel model = response.body();
                if (model != null) {
                    List<Datum> data = model.getData();
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
            public void onFailure(Call<SimilarProductsModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
        RetroWeb.getClient().create(ServiceApi.class).onGetSimilarProductsModel("Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<SimilarProductsModel>() {
            @Override
            public void onResponse(Call<SimilarProductsModel> call, Response<SimilarProductsModel> response) {
                SimilarProductsModel model = response.body();
                if (model != null) {
                    List<Datum> data = model.getData();
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
            public void onFailure(Call<SimilarProductsModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }
}
