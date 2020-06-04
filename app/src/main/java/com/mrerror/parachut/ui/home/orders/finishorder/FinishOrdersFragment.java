package com.mrerror.parachut.ui.home.orders.finishorder;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Adabters.OrdersAdapter;
import com.mrerror.parachut.Models.FinishedOrders.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.FinishOrdersFragmentBinding;

public class FinishOrdersFragment extends Fragment {

    private FinishOrdersViewModel mViewModel;
    FinishOrdersFragmentBinding finishOrdersFragmentBinding;

    public static FinishOrdersFragment newInstance() {
        return new FinishOrdersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        finishOrdersFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.finish_orders_fragment , container , false);
        return finishOrdersFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel =new FinishOrdersViewModel(getContext());
        // TODO: Use the ViewModel
        finishOrdersFragmentBinding.setLifecycleOwner(this);
        finishOrdersFragmentBinding.setFinishOrdersVmodel(mViewModel);

        setupFinishedOrders();
    }


    private void setupFinishedOrders() {
        final OrdersAdapter adapter = new OrdersAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        finishOrdersFragmentBinding.rvFinishedOrders.setLayoutManager(manager);
        mViewModel.mutableLiveDataOrdersPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {
                adapter.submitList(data);
                Log.e("finishXXXCXC", adapter.getItemCount() + "");
            }
        });

        finishOrdersFragmentBinding.rvFinishedOrders.setAdapter(adapter);
    }


}
