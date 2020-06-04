package com.mrerror.parachut.ui.home.profilefragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.mrerror.parachut.Models.UserData.GetUserData;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ProfileFragmentBinding;
import com.mrerror.parachut.ui.home.profilefragment.editprofile.EditProfileFragment;
import com.mrerror.parachut.utils.GlobalPrefrencies;
import com.mrerror.parachut.utils.Utils;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    ProfileFragmentBinding profileFragmentBinding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        profileFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.profile_fragment, container, false);
        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(),globalPrefrencies.getLanguage());



        return profileFragmentBinding.getRoot();
    }
    GlobalPrefrencies globalPrefrencies;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        profileFragmentBinding.setProfileVmodel(mViewModel);
        profileFragmentBinding.setLifecycleOwner(this);

        profileFragmentBinding.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new EditProfileFragment());

            }
        });

        mViewModel.onGetUserData(getContext());

        mViewModel.userDataMutableLiveData.observe(getViewLifecycleOwner(), new Observer<GetUserData>() {
            @Override
            public void onChanged(GetUserData getUserData) {
                Log.e("XSX", new Gson().toJson(getUserData));

                profileFragmentBinding.nameId.setText(getUserData.getData().getName() + "");
                profileFragmentBinding.phoneId.setText(getUserData.getData().getMobile() + "");
                profileFragmentBinding.addressId.setText(getUserData.getData().getAddress() + "");
            }
        });
    }
    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.containerprofile, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
