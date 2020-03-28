package com.mrerror.parachut.ui.home.profilefragment;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.GetUserData;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    MutableLiveData<GetUserData> userDataMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;
    GetUserData model;


    public void onGetUserData(Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        model = null;

        Log.e("ERROR", globalPrefrencies.getApi_token() + " ");
        RetroWeb.getClient().create(ServiceApi.class).onGetUserData("Bearer" + globalPrefrencies.getApi_token()).enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(Call<GetUserData> call, Response<GetUserData> response) {
                if (response.body() != null) {
                    model = response.body();
                    userDataMutableLiveData.setValue(response.body());
                    String name = model.getName();
                    String phone = model.getMobile();
                    String address = model.getAddress();
                    //Password !!!

                    globalPrefrencies.storeName(name);
                    globalPrefrencies.storePhone(phone);
                    globalPrefrencies.storeAddress(address);
                }
            }

            @Override
            public void onFailure(Call<GetUserData> call, Throwable t) {
                Log.e("Error  ," ,t.getMessage());
            }
        });

    }





}
