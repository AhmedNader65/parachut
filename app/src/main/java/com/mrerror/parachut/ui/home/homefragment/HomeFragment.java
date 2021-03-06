package com.mrerror.parachut.ui.home.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Adabters.CategoryAdabter;
import com.mrerror.parachut.Adabters.OffersAdapters;
import com.mrerror.parachut.Adabters.SuperMarketAdapters;
import com.mrerror.parachut.Models.CategoryModel.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.HomeFragmentBinding;
import com.mrerror.parachut.ui.home.allitem.AllItemActivity;
import com.mrerror.parachut.ui.home.homefragment.categoryfragment.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    public static List<Datum> dataArrayListProduct = new ArrayList<>();

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
        // TODO: Use the ViewModel


        mViewModel =new HomeViewModel(getContext());
        homeFragmentBinding.setHomeVmodel(mViewModel);
        homeFragmentBinding.setLifecycleOwner(this);


        seupOffers();
        setupCategory();
        setupStores();

        homeFragmentBinding.moreCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new CategoryFragment());

            }
        });
        homeFragmentBinding.moreOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAllItemActivity("Offers");
            }
        });
        homeFragmentBinding.moreStores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllItemActivity("Stores");
            }
        });

    }
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.containerhome, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
    private void openAllItemActivity(String value) {

        Intent intent=new Intent(getContext(), AllItemActivity.class);
        intent.putExtra("key",value);
        startActivity(intent);

    }

    private void setupStores() {

         final SuperMarketAdapters adapter = new SuperMarketAdapters();
        homeFragmentBinding.stores.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.mutableLiveDataSuperMarketPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<com.mrerror.parachut.Models.SuperMarket.Datum>>() {
                    @Override
                    public void onChanged(PagedList<com.mrerror.parachut.Models.SuperMarket.Datum> data) {


                        adapter.submitList(data);

                    }
                });
                homeFragmentBinding.stores.setAdapter(adapter);
        recycle_animation(homeFragmentBinding.stores);
    }

    private void setupCategory() {
        final CategoryAdabter adapter = new CategoryAdabter();
        homeFragmentBinding.categories.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        mViewModel.mutableLiveDataCategoryPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {
                adapter.submitList(data);
            }
        });
        homeFragmentBinding.categories.setAdapter(adapter);
        recycle_animation(homeFragmentBinding.categories);

    }

    private void seupOffers() {

        final OffersAdapters adapter = new OffersAdapters();
        homeFragmentBinding.offers.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        mViewModel.mutableLiveDataOffersPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<com.mrerror.parachut.Models.Datum>>() {
            @Override
            public void onChanged(PagedList<com.mrerror.parachut.Models.Datum> data) {
                adapter.submitList(data);
                recycle_animation(homeFragmentBinding.offers);
            }
        });

        homeFragmentBinding.offers.setAdapter(adapter);
        recycle_animation(homeFragmentBinding.offers);

    }

    private void recycle_animation(RecyclerView recyclerView) {
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), R.anim.layout_animation_fall_down));
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

}
