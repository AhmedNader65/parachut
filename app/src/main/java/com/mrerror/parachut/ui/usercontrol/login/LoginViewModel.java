package com.mrerror.parachut.ui.usercontrol.login;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.Login.UserLoginModel;
import com.mrerror.parachut.Models.Register.UserRegisterModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<UserLoginModel> userLoginModelMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;
    UserLoginModel model;

    public UserLoginModel onClickLogin(String mobile, String password, final Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        model = null;

        RetroWeb.getClient().create(ServiceApi.class).onLogin(mobile, password).enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                if (response.body().getStatus().equals("success")) {
                    model = response.body();
                    userLoginModelMutableLiveData.setValue(response.body());

                }

            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {

                Log.e("Error Login ," ,t.getMessage());

            }

            });

        return model;
    }

}
