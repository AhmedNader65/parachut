package com.mrerror.parachut.ui.home.fastorder;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.FastOrder.FastOrderModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FastOrderViewModel extends ViewModel {

    MutableLiveData<FastOrderModel> fastOrderModelMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;

    public void onSendFastOrder(final Context context, String address, String mabile, String oreder, String lat, String longetued) {
        globalPrefrencies = new GlobalPrefrencies(context);
        Log.e("ERROR", globalPrefrencies.getApi_token() + " ");
        RetroWeb.getClient().create(ServiceApi.class).onSendFastOrder(address, mabile, lat, longetued, oreder, "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<FastOrderModel>() {
            @Override
            public void onResponse(Call<FastOrderModel> call, Response<FastOrderModel> response) {
                if (response.body() != null && response.body().getStatus()) {
                    fastOrderModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FastOrderModel> call, Throwable t) {

            }
        });
    }

}

