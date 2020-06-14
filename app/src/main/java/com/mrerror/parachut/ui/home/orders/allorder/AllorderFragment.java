package com.mrerror.parachut.ui.home.orders.allorder;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Adabters.PendingOrdersAdapter;
import com.mrerror.parachut.Models.PendingOrders.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.AllorderFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class AllorderFragment extends Fragment {

    private AllorderViewModel mViewModel;
    AllorderFragmentBinding allorderFragmentBinding;
    public static List<Datum> dataArrayListProduct = new ArrayList<>();

    public static AllorderFragment newInstance() {
        return new AllorderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       allorderFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.allorder_fragment , container,false);
        return allorderFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        mViewModel = ViewModelProviders.of(this).get(AllorderViewModel.class);
        allorderFragmentBinding.setLifecycleOwner(this);
        allorderFragmentBinding.setAllorderVmodel(mViewModel);


        mViewModel.init(getContext());
        mViewModel.initAll(getContext());
        setupPendingOrders();

    }

    private void setupPendingOrders()
    {
        final PendingOrdersAdapter adapter = new PendingOrdersAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        allorderFragmentBinding.rvPendingOrders.setLayoutManager(manager);
        mViewModel.mutableLiveDataAllOrdersPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {

                adapter.submitList(data);
                Log.e("sscsc", "scscsccccc");
            }
        });
        allorderFragmentBinding.rvPendingOrders.setAdapter(adapter);


    }

}
