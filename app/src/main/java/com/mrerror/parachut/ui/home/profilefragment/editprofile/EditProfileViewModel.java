package com.mrerror.parachut.ui.home.profilefragment.editprofile;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.UserData.GetUserData;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileViewModel extends ViewModel {
    MutableLiveData<GetUserData> userDataMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;
    GetUserData model;

    MutableLiveData<GetUserData> muserDataMutableLiveData = new MutableLiveData<>();

    public void onClickUpdate(String name, String address, String mobile, String password, String email, String lat, String longt, Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        model = null;

        RetroWeb.getClient().create(ServiceApi.class).onUpdateUser(name, address, mobile, password, email, lat, longt, "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(Call<GetUserData> call, Response<GetUserData> response) {
                if (response.body() != null) {
                    model = response.body();
                    userDataMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetUserData> call, Throwable t) {

                Log.e("Error  ," ,t.getMessage());
            }
        });
    }

    public void onGetUserData(Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        model = null;

        Log.e("MRxx", globalPrefrencies.getApi_token() + " ");
        RetroWeb.getClient().create(ServiceApi.class).onGetUserData("Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(Call<GetUserData> call, Response<GetUserData> response) {
                if (response.body() != null) {
                    model = response.body();
                    muserDataMutableLiveData.setValue(response.body());
                    String name = model.getData().getName();
                    String phone = model.getData().getMobile();
                    String address = model.getData().getAddress();
                    //Password !!!

                    globalPrefrencies.storeName(name);
                    globalPrefrencies.storePhone(phone);
                    globalPrefrencies.storeAddress(address);
                }
            }

            @Override
            public void onFailure(Call<GetUserData> call, Throwable t) {
                Log.e("Error  ,", t.getMessage());
            }
        });

    }


}
