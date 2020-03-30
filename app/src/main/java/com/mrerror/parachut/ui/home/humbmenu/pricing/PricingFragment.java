package com.mrerror.parachut.ui.home.humbmenu.pricing;

import android.os.Bundle;
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

import com.mrerror.parachut.Adabters.PricingAdapter;
import com.mrerror.parachut.Models.Pricing.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.PricingFragmentBinding;

public class PricingFragment extends Fragment {

    private PricingViewModel mViewModel;
    PricingFragmentBinding pricingFragmentBinding;

    public static PricingFragment newInstance() {
        return new PricingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        pricingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.pricing_fragment, container, false);
        return pricingFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PricingViewModel.class);
        // TODO: Use the ViewModel
        pricingFragmentBinding.setHomeVmodel(mViewModel);
        pricingFragmentBinding.setLifecycleOwner(this);

        setupPrices();

        pricingFragmentBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    private void setupPrices() {

        final PricingAdapter adapter = new PricingAdapter();
        pricingFragmentBinding.rvPricing.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.mutableLiveDataPricePageList.observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> datum) {
                adapter.submitList(datum);
            }
            });

        pricingFragmentBinding.rvPricing.setAdapter(adapter);
    }

}