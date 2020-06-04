package com.mrerror.parachut.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.BaseModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class CartViewMmodel extends ViewModel {

    MutableLiveData<BaseModel> baseModelMutableLiveData=new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;
    public void sendToDB(String jsonArrayOfCart, double latitude, double longitude, final Context context) {
globalPrefrencies=new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onPay("00","0000",latitude+"",longitude+"",jsonArrayOfCart,"Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                baseModelMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {

                Log.e("5555",t.getMessage());
            }
        });


    }
}
