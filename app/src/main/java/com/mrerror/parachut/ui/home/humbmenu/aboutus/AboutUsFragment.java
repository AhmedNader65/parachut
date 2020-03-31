package com.mrerror.parachut.ui.home.humbmenu.aboutus;

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

import com.mrerror.parachut.Models.AboutUs.AboutUsModel;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.AboutUsFragmentBinding;
import com.mrerror.parachut.utils.GlobalPrefrencies;
import com.mrerror.parachut.utils.Utils;

public class AboutUsFragment extends Fragment {

    private AboutUsViewModel mViewModel;
    AboutUsFragmentBinding aboutUsFragmentBinding;

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        aboutUsFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.about_us_fragment, container, false);
        Utils.setLocale(getContext());

        return aboutUsFragmentBinding.getRoot();
    }

    GlobalPrefrencies globalPrefrencies;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AboutUsViewModel.class);
        // TODO: Use the ViewModel
        aboutUsFragmentBinding.setLifecycleOwner(this);
        aboutUsFragmentBinding.setAboutUsVmodel(mViewModel);
        globalPrefrencies = new GlobalPrefrencies(getContext());

        mViewModel.getAboutUs(getContext());

        aboutUsFragmentBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mViewModel.aboutUsModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<AboutUsModel>() {
            @Override
            public void onChanged(AboutUsModel aboutUsModel) {

                aboutUsFragmentBinding.tvWho.setText(aboutUsModel.getData().get(0).getWhoUs());
                aboutUsFragmentBinding.tvFeature.setText(aboutUsModel.getData().get(0).getFeature());

            }
        });
    }

}
