package com.mrerror.parachut.ui.usercontrol.login;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrerror.parachut.Models.Login.UserLoginModel;
import com.mrerror.parachut.Models.Register.UserRegisterModel;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.LoginFragmentBinding;
import com.mrerror.parachut.utils.Utils;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    LoginFragmentBinding loginFragmentBinding;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        return loginFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginFragmentBinding.setLoginVmodel(mViewModel);
        loginFragmentBinding.setLifecycleOwner(this);
        // TODO: Use the ViewModel

        loginFragmentBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCheackValidation()) {
                    UserLoginModel userLoginModel = mViewModel.onClickLogin(
                            loginFragmentBinding.phoneId.getText().toString(),
                            loginFragmentBinding.passId.getText().toString().trim()
                            ,getContext());
                }else {

                }

            }
        });
    }

    public boolean onCheackValidation() {

        if (!ValidatePassword()) {
            return false;
        }

        if (!ValidatePhone()) {
            return false;
        }

        return true;
    }


    private boolean ValidatePhone() {
        if (loginFragmentBinding.phoneId.getText().toString().trim().isEmpty()) {
            loginFragmentBinding.phoneId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(loginFragmentBinding.phoneId, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidatePassword() {
        if (loginFragmentBinding.passId.getText().toString().trim().isEmpty()) {
            loginFragmentBinding.passId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(loginFragmentBinding.passId, getActivity().getWindow());
            return false;
        }
        return true;
    }

     }

