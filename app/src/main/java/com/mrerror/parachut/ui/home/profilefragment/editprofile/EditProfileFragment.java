package com.mrerror.parachut.ui.home.profilefragment.editprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.mrerror.parachut.Models.GetUserData;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.EditProfileFragmentBinding;
import com.mrerror.parachut.ui.home.profilefragment.ProfileFragment;
import com.mrerror.parachut.utils.GlobalPrefrencies;

public class EditProfileFragment extends Fragment {

    EditProfileViewModel mViewModel;
    EditProfileFragmentBinding editProfileFragmentBinding;

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        editProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.edit_profile_fragment, container, false);
        return editProfileFragmentBinding.getRoot();
    }
    GlobalPrefrencies globalPrefrencies;
    GetUserData model;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);

        editProfileFragmentBinding.setProfileVmodel(mViewModel);
        editProfileFragmentBinding.setLifecycleOwner(this);
        globalPrefrencies = new GlobalPrefrencies(getContext());

        editProfileFragmentBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUserData userModel = mViewModel.onClickUpdate(
                        editProfileFragmentBinding.nameId.getText().toString().trim(),
                        editProfileFragmentBinding.addressId.getText().toString().trim(),
                        editProfileFragmentBinding.phoneId.getText().toString(),
                        editProfileFragmentBinding.passId.getText().toString().trim(),
                        "",
                        "30.000","33.000"

                        ,getContext());

                showFragment(new ProfileFragment());
            }
        });


    }
    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
