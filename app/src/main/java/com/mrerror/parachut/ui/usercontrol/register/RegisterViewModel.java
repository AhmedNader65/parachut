package com.mrerror.parachut.ui.usercontrol.register;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.mrerror.parachut.Models.Register.UserRegisterModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.ui.usercontrol.OTP.OTPActivity;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<UserRegisterModel> userRegisterModelMutableLiveData=new MutableLiveData<>();

    GlobalPrefrencies globalPrefrencies;

    UserRegisterModel  model;

    public void onClickRegister(String name, String phone, String address, String password, String lat, String longit, String confirmPassword, final Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);
        model = null;

        RetroWeb.getClient().create(ServiceApi.class).onRegester(name, phone, address, lat, longit, password, confirmPassword).enqueue(new Callback<UserRegisterModel>() {
            @Override
            public void onResponse(Call<UserRegisterModel> call, Response<UserRegisterModel> response) {
                Log.e("SSDSDDDD", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    model=response.body();
                    userRegisterModelMutableLiveData.setValue(response.body());
                    Intent intent = new Intent(context, OTPActivity.class);
                    intent.putExtra("code", response.body());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);

                }else {
                    //HANDEL ERROR HERE


                }
            }

            @Override
            public void onFailure(Call<UserRegisterModel> call, Throwable t) {

                Log.e("Error Register ," ,t.getMessage());
            }
        });

    }


}
