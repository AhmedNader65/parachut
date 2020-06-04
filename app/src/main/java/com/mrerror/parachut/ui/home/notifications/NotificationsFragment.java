package com.mrerror.parachut.ui.home.notifications;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrerror.parachut.Adabters.NotificationAdapter;
import com.mrerror.parachut.Models.NotificationsModel.GetNotification;
import com.mrerror.parachut.Models.PendingOrders.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.NotificationsFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel mViewModel;

    NotificationsFragmentBinding notificationsFragmentBinding;
    public static NotificationsFragment newInstance() {
        return new NotificationsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        notificationsFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.notifications_fragment, container, false);
        return notificationsFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        // TODO: Use the ViewModel
        notificationsFragmentBinding.setNotificationVmodel(mViewModel);
        notificationsFragmentBinding.setLifecycleOwner(this);


        setupNotification();

    }

    ArrayList<GetNotification> getNotificationArrayList=new ArrayList<>();
    private void setupNotification() {

        final NotificationAdapter adapter=new NotificationAdapter();
        notificationsFragmentBinding.rvNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getNotificationMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<GetNotification>>() {
            @Override
            public void onChanged(List<GetNotification> getNotifications) {
                if(getNotifications.size()>0) {
                     notificationsFragmentBinding.txtnofound.setVisibility(View.GONE);
                    getNotificationArrayList.addAll(getNotifications);
                    adapter.setAllNotifications(getNotificationArrayList);
                }else {
                    notificationsFragmentBinding.txtnofound.setVisibility(View.VISIBLE);
                }
            }
        });
        notificationsFragmentBinding.rvNotification.setAdapter(adapter);

    }

}
