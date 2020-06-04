package com.mrerror.parachut.ui.home.orders.detailsorder;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.DetailsOrder.DetailsOrderModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsOrderViewModel extends ViewModel {

    MutableLiveData<DetailsOrderModel> detailsOrderModelMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;

    public void getOrderDetailss(final Context context, String id_) {
        globalPrefrencies = new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetDetailsOrder(id_, globalPrefrencies.getLanguage(),"Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<DetailsOrderModel>() {
            @Override
            public void onResponse(Call<DetailsOrderModel> call, Response<DetailsOrderModel> response) {
                detailsOrderModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DetailsOrderModel> call, Throwable t) {
                Log.e("ZZX", t.getMessage());
            }
        });
    }
}
