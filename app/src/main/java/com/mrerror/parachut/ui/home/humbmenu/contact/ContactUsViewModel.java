package com.mrerror.parachut.ui.home.humbmenu.contact;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.ContactUs.ContactUsModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<ContactUsModel> contactUsModelMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;
    ContactUsModel model;

    public void onClickSend(Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        model = null;

        RetroWeb.getClient().create(ServiceApi.class).onGetMessage("Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<ContactUsModel>() {
            @Override
            public void onResponse(Call<ContactUsModel> call, Response<ContactUsModel> response) {
                if (response.body() != null && response.body().getStatus()) {
                    contactUsModelMutableLiveData.setValue(response.body());
                    model = response.body();
                }

            }

            @Override
            public void onFailure(Call<ContactUsModel> call, Throwable t) {

            }
        });
    }
}
