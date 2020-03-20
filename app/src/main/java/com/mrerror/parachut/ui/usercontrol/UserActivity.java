package com.mrerror.parachut.ui.usercontrol;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;

import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityUserBinding;
import com.mrerror.parachut.ui.usercontrol.login.LoginFragment;
import com.mrerror.parachut.ui.usercontrol.register.RegisterFragment;
import com.mrerror.parachut.utils.Utils;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding activityUserBinding;
    UserActivityViewModel userActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUserBinding=DataBindingUtil.setContentView(this,R.layout.activity_user);
        userActivityViewModel= ViewModelProviders.of(this).get(UserActivityViewModel.class);
        activityUserBinding.setLifecycleOwner(this);
        activityUserBinding.setUserVmodel(userActivityViewModel);
        Utils.setLocale(this);
        showFragment(new RegisterFragment());

    }
    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

}
