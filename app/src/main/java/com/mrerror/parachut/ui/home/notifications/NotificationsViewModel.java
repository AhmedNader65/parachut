package com.mrerror.parachut.ui.home.notifications;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.DetailsOrder.DetailsOrderModel;
import com.mrerror.parachut.Models.NotificationsModel.GetNotification;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    MutableLiveData<List<GetNotification>> getNotificationMutableLiveData = new MutableLiveData<>();
    GlobalPrefrencies globalPrefrencies;

    public void getNotifications(final Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetNotifications( globalPrefrencies.getLanguage(),"Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<List<GetNotification>>() {
            @Override
            public void onResponse(Call<List<GetNotification>> call, Response<List<GetNotification>> response) {
                getNotificationMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<GetNotification>> call, Throwable t) {
                Log.e("ZZX", t.getMessage());
            }
        });
    }
}
