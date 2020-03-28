package com.mrerror.parachut.ui.home.EditProfileFragment;

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

public class EditProfileViewModel extends ViewModel {
    MutableLiveData<GetUserData> userDataMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;
    GetUserData model;

    public GetUserData onClickUpdate(String name, String address, String mobile, String password, String email, String lat, String longt, Context context) {
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

        return model;
    }

}
