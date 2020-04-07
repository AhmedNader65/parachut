package com.mrerror.parachut.Models.AllProductStores;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.mrerror.parachut.Models.AllProductCategories.AllProductCategories;
import com.mrerror.parachut.Models.Datum;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductStoresDataSource extends PageKeyedDataSource<Long, Datum> {
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;

    String id_;
    public ProductStoresDataSource(String id__) {
        this.id_=id__;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllProductStores(id_,FIRST_PAGE).enqueue(new Callback<AllProductCategories>() {
            @Override
            public void onResponse(Call<AllProductCategories> call, Response<AllProductCategories> response) {
                AllProductCategories body = response.body();
                if (body != null) {
                    List<Datum> data = body.getData();
                    callback.onResult(data, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<AllProductCategories> call, Throwable t) {

                Log.e("xxx",t.getMessage());
            }
        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllProductStores(id_,params.key).enqueue(new Callback<AllProductCategories>() {
            @Override
            public void onResponse(Call<AllProductCategories> call, Response<AllProductCategories> response) {
                AllProductCategories body = response.body();
                if (body != null) {
                    List<Datum> data = body.getData();
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
            public void onFailure(Call<AllProductCategories> call, Throwable t) {
                Log.e("yyy",t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllProductStores(id_,params.key).enqueue(new Callback<AllProductCategories>() {
            @Override
            public void onResponse(Call<AllProductCategories> call, Response<AllProductCategories> response) {
                AllProductCategories body = response.body();
                if (body != null) {
                    List<Datum> data = body.getData();
                    callback.onResult(data, params.key + 1);
                }

            }

            @Override
            public void onFailure(Call<AllProductCategories> call, Throwable t) {
                Log.e("zzz",t.getMessage());
            }
        });

    }
}

