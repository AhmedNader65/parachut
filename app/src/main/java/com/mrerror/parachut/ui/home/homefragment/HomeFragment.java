package com.mrerror.parachut.ui.home.homefragment;

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

import com.mrerror.parachut.Adabters.CategoryAdabter;
import com.mrerror.parachut.Models.CategoryModel.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    HomeFragmentBinding homeFragmentBinding;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        return homeFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel

        homeFragmentBinding.setHomeVmodel(mViewModel);
        homeFragmentBinding.setLifecycleOwner(this);

        seupOffers();
        setupCategory();
        setupStores();

    }

    private void setupStores() {

    }

    private void setupCategory() {
        final CategoryAdabter adapter = new CategoryAdabter();
        homeFragmentBinding.categories.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.mutableLiveDataCategoryPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {
                adapter.submitList(data);
            }
        });
        homeFragmentBinding.categories.setAdapter(adapter);
    }

    private void seupOffers() {

    }

}
