package com.mrerror.parachut.ui.home.homefragment.categoryfragment;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrerror.parachut.Adabters.AllCategoryAdabter;
import com.mrerror.parachut.Adabters.CategoryAdabter;
import com.mrerror.parachut.Models.CategoryModel.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.CategoryFragmentBinding;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mViewModel;


    CategoryFragmentBinding categoryFragmentBinding;
    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        categoryFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.category_fragment, container, false);
        return  categoryFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel=new CategoryViewModel(getContext());
        categoryFragmentBinding.setCategoriedVmodel(mViewModel);
        categoryFragmentBinding.setLifecycleOwner(this);

        categoryFragmentBinding.mostCategoryProgress.setVisibility(View.VISIBLE);
        categoryFragmentBinding.allCategoryProgress.setVisibility(View.VISIBLE);

        setMostCategory();
        setupCategory();
        // TODO: Use the ViewModel
    }

    private void setupCategory() {
        final AllCategoryAdabter adapter = new AllCategoryAdabter();
        categoryFragmentBinding.allcategories.setLayoutManager(new GridLayoutManager(getContext(),4,RecyclerView.VERTICAL,false));
        mViewModel.mutableLiveDataAllCategoryPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<com.mrerror.parachut.Models.AllCattegoryModel.Datum>>() {
            @Override
            public void onChanged(PagedList<com.mrerror.parachut.Models.AllCattegoryModel.Datum> data) {
                adapter.submitList(data);
                categoryFragmentBinding.allCategoryProgress.setVisibility(View.INVISIBLE);

            }

    });
        categoryFragmentBinding.allcategories.setAdapter(adapter);
    }

    private void setMostCategory() {
        final CategoryAdabter adapter = new CategoryAdabter();
        categoryFragmentBinding.categories.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        mViewModel.mutableLiveDataCategoryPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {
                adapter.submitList(data);
                categoryFragmentBinding.mostCategoryProgress.setVisibility(View.INVISIBLE);
            }
        });
        categoryFragmentBinding.categories.setAdapter(adapter);

    }

}
