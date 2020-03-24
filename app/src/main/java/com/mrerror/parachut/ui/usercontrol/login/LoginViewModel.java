package com.mrerror.parachut.ui.usercontrol.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.LogIn.UserLoginModel;

import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.ui.home.MainActivity;
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
                if (response.body().getStatus()) {
                    model = response.body();
                    userLoginModelMutableLiveData.setValue(response.body());
                    int id = model.getUser().getId();
                    String name = model.getUser().getName();
                    String phone = model.getUser().getMobile();
                    String api_token = model.getUser().getApiToken();


                    Toast.makeText(context, "مرحبا بك " + name, Toast.LENGTH_LONG).show();

                    globalPrefrencies.storeLoginStatus(true);
                    globalPrefrencies.storeUserId(id);
                    globalPrefrencies.storeName(name);
                    globalPrefrencies.storePhone(phone);
                    globalPrefrencies.storeApi_token(api_token);
                    globalPrefrencies.storeLoginStatus(true);

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    context.startActivity(intent);
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
