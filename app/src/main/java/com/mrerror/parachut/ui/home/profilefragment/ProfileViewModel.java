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

    public void onGetUserData(Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        Log.e("ERROR", globalPrefrencies.getApi_token() + " ");
        RetroWeb.getClient().create(ServiceApi.class).onGetUserData("Bearer" + globalPrefrencies.getApi_token()).enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(Call<GetUserData> call, Response<GetUserData> response) {
                if (response != null) {
                    userDataMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetUserData> call, Throwable t) {

            }
        });
    }


}
