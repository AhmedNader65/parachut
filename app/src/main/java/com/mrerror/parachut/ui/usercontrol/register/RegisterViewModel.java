package com.mrerror.parachut.ui.usercontrol.register;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.Register.UserRegisterModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<UserRegisterModel> userRegisterModelMutableLiveData;
    Context context;
    GlobalPrefrencies globalPrefrencies;
    public RegisterViewModel(Context context){
        this.context=context;
        globalPrefrencies=new GlobalPrefrencies(context);
    }
    UserRegisterModel  model;
    public UserRegisterModel onClickRegister(String name,String phone, String address, String password,String lat, String lang, String confirmPassword){
        model = null;
        userRegisterModelMutableLiveData=new MutableLiveData<>();
        RetroWeb.getClient().create(ServiceApi.class).onRegester(name,phone,address,lat,lang,password,confirmPassword).enqueue(new Callback<UserRegisterModel>() {
            @Override
            public void onResponse(Call<UserRegisterModel> call, Response<UserRegisterModel> response) {
                if(response.body().getStatus().equals("success")){
                    model=response.body();
                    userRegisterModelMutableLiveData.setValue(response.body());
                    int id = model.getUser().getId();
                    String name = model.getUser().getName();
                    String phone = model.getUser().getMobile();
                    String api_token = model.getUser().getToken();


                    Toast.makeText(context, "مرحبا بك " + name, Toast.LENGTH_LONG).show();

                    globalPrefrencies.storeLoginStatus(true);
                    globalPrefrencies.storeUserId(id);
                    globalPrefrencies.storeName(name);
                    globalPrefrencies.storePhone(phone);
                    globalPrefrencies.storeApi_token(api_token);
                    globalPrefrencies.storeLoginStatus(true);

                    Intent intent = new Intent(context, HomeActivity.class);
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

        return model;

    }


}
