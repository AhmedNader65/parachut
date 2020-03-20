package com.mrerror.parachut.ui.usercontrol.register;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mrerror.parachut.Models.Register.UserRegisterModel;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.RegisterFragmentBinding;
import com.mrerror.parachut.utils.Utils;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    RegisterFragmentBinding registerFragmentBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        registerFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.register_fragment, container, false);
        return registerFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
        registerFragmentBinding.setRegisterVmodel(mViewModel);
        registerFragmentBinding.setLifecycleOwner(this);


        registerFragmentBinding.RegisterId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCheackValidation()) {
                    if(id_City!=0&&id_Country!=0) {
                        UserRegisterModel userRegisterModel = mViewModel.onClickRegister(
                                registerFragmentBinding.nameId.getText().toString().trim(),
                                registerFragmentBinding.emailId.getText().toString().trim(),
                                registerFragmentBinding.passId.getText().toString().trim(),
                                registerFragmentBinding.conpassid.getText().toString().trim(),
                                registerFragmentBinding.phoneId.getText().toString().trim());
                    }else {
                        Toast.makeText(getContext(), "قم باختيار العنوان اولا", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public boolean onCheackValidation() {

        if (!ValidateName()) {
            return false;
        }
        if (!ValidatePassword()) {
            return false;

        }
        if (!ValidatePasswordRepeat()) {
            return false;
        }
        if (!ValidatePhone()) {
            return false;
        }
        if (!ValidateEmail()) {
            return false;
        }
        return true;
    }


    private boolean ValidateName() {
        if (registerFragmentBinding.nameId.getText().toString().trim().isEmpty()) {
            registerFragmentBinding.nameId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(registerFragmentBinding.nameId, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidatePhone() {
        if (registerFragmentBinding.phoneId.getText().toString().trim().isEmpty()) {
            registerFragmentBinding.phoneId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(registerFragmentBinding.phoneId, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidatePassword() {
        if (registerFragmentBinding.passId.getText().toString().trim().isEmpty()) {
            registerFragmentBinding.passId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(registerFragmentBinding.passId, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidatePasswordRepeat() {
        if (registerFragmentBinding.conpassid.getText().toString().trim().isEmpty()) {
            registerFragmentBinding.conpassid.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(registerFragmentBinding.conpassid, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidateEmail() {
        if (registerFragmentBinding.addressId.getText().toString().trim().isEmpty()) {
            registerFragmentBinding.addressId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(registerFragmentBinding.addressId, getActivity().getWindow());
            return false;
        }
        return true;
    }

}
