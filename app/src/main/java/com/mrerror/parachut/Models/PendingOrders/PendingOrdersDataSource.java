package com.mrerror.parachut.Models.PendingOrders;

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

public class PendingOrdersDataSource extends PageKeyedDataSource<Long , Datum> {
    GlobalPrefrencies globalPrefrencies;
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;
    Context context;

    public PendingOrdersDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Datum> callback) {
        globalPrefrencies = new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetPendingOrders(globalPrefrencies.getLanguage(), FIRST_PAGE, "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<PendingOrdersModel>() {
            @Override
            public void onResponse(Call<PendingOrdersModel> call, Response<PendingOrdersModel> response) {
                PendingOrdersModel model = response.body();
                if (model!= null) {
                    List<Datum> data = model.getData();
                    callback.onResult(data, null, FIRST_PAGE + 1);
                }

            }

            @Override
            public void onFailure(Call<PendingOrdersModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
        globalPrefrencies = new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetPendingOrders(globalPrefrencies.getLanguage(), params.key, "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<PendingOrdersModel>() {
            @Override
            public void onResponse(Call<PendingOrdersModel> call, Response<PendingOrdersModel> response) {
                PendingOrdersModel model = response.body();
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
            public void onFailure(Call<PendingOrdersModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
        globalPrefrencies = new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetPendingOrders(globalPrefrencies.getLanguage(), params.key, "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<PendingOrdersModel>() {
            @Override
            public void onResponse(Call<PendingOrdersModel> call, Response<PendingOrdersModel> response) {
                PendingOrdersModel model = response.body();

                if (model != null) {
                    List<Datum> data = model.getData();
                    callback.onResult(data, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<PendingOrdersModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }
//    @Override
//    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Datum> callback) {
//        globalPrefrencies = new GlobalPrefrencies(context);
//
//        RetroWeb.getClient().create(ServiceApi.class).onGetPendingOrders(globalPrefrencies.getLanguage(),"Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<PendingOrdersModel>() {
//            @Override
//            public void onResponse(Call<PendingOrdersModel> call, Response<PendingOrdersModel> response) {
//                PendingOrdersModel model = response.body();
//                if (model != null) {
//                    List<Datum> data = model.getData();
//                    Log.e("xx1",data.size()+"");
//                    callback.onResult(data , null , FIRST_PAGE + 1);
//                }
//                Log.e("xx1","nulll");
//            }
//
//            @Override
//            public void onFailure(Call<PendingOrdersModel> call, Throwable t) {
//                t.getLocalizedMessage();
//            }
//        });
//    }
//
//    @Override
//    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
//        RetroWeb.getClient().create(ServiceApi.class).onGetPendingOrders(globalPrefrencies.getLanguage(),"Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<PendingOrdersModel>() {
//            @Override
//            public void onResponse(Call<PendingOrdersModel> call, Response<PendingOrdersModel> response) {
//                PendingOrdersModel model = response.body();
//                if (model != null) {
//                    List<Datum> data = model.getData();
//                    long key;
//                    if (params.key > 1) {
//                        key = params.key - 1;
//                    } else {
//                        key = 0;
//                    }
//                    callback.onResult(data, key);
//                    Log.e("xx2",data.size()+"");
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<PendingOrdersModel> call, Throwable t) {
//                t.getLocalizedMessage();
//            }
//        });
//    }
//
//
//    @Override
//    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
//        RetroWeb.getClient().create(ServiceApi.class).onGetPendingOrders(globalPrefrencies.getLanguage(), "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<PendingOrdersModel>() {
//            @Override
//            public void onResponse(Call<PendingOrdersModel> call, Response<PendingOrdersModel> response) {
//                PendingOrdersModel model = response.body();
//                if (model != null) {
//                    List<Datum> data = model.getData();
//                    callback.onResult(data, params.key + 1);
//                    Log.e("xx3",data.size()+"");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<PendingOrdersModel> call, Throwable t) {
//                t.getLocalizedMessage();
//            }
//        });
//    }

}
