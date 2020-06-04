package com.mrerror.parachut.ui.home.humbmenu.aboutus;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.AboutUs.AboutUsModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<AboutUsModel> aboutUsModelMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;
    AboutUsModel model;

    public void getAboutUs(Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        model = null;

        RetroWeb.getClient().create(ServiceApi.class).OnGetAboutUs(globalPrefrencies.getLanguage()).enqueue(new Callback<AboutUsModel>() {
            @Override
            public void onResponse(Call<AboutUsModel> call, Response<AboutUsModel> response) {
                if (response.body() != null) {
                aboutUsModelMutableLiveData.setValue(response.body());
                model = response.body();
            }
            }

            @Override
            public void onFailure(Call<AboutUsModel> call, Throwable t) {

            }
        });
    }
}
