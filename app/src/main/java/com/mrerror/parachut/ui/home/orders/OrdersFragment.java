package com.mrerror.parachut.ui.home.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.OrdersFragmentBinding;
import com.mrerror.parachut.ui.home.orders.allorder.AllorderFragment;
import com.mrerror.parachut.ui.home.orders.finishorder.FinishOrdersFragment;
import com.mrerror.parachut.utils.PagerAdaptar;

public class OrdersFragment extends Fragment {

    private OrdersViewModel mViewModel;

    OrdersFragmentBinding ordersFragmentBinding;
    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    PagerAdaptar adaptar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ordersFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.orders_fragment, container, false);

        return ordersFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        ordersFragmentBinding.setOrderVmodel(mViewModel);
        ordersFragmentBinding.setLifecycleOwner(this);
        // TODO: Use the ViewModel
        initWidget();
    }

    private void initWidget() {

        adaptar = new PagerAdaptar(getFragmentManager());
        adaptar.addNewFragment(new FinishOrdersFragment());
        adaptar.addNewFragment(new AllorderFragment());
        ordersFragmentBinding.tabLayout.setupWithViewPager(ordersFragmentBinding.viewpager);
        ordersFragmentBinding.viewpager.setAdapter(adaptar);
        setUpTabsTopPager();

    }

    private void setUpTabsTopPager() {
        ordersFragmentBinding.tabLayout.getTabAt(0).setText("تم التنفيذ");
        ordersFragmentBinding.tabLayout.getTabAt(1).setText("قيد التنفيذ");
        //tabLayout.setTabTextColors(Color.parseColor("#FFAFAFAF"), Color.parseColor("#000000"));

    }
}
