package com.mrerror.parachut.ui.usercontrol;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityUserBinding;
import com.mrerror.parachut.ui.usercontrol.login.LoginFragment;
import com.mrerror.parachut.ui.usercontrol.register.RegisterFragment;
import com.mrerror.parachut.utils.GlobalPrefrencies;
import com.mrerror.parachut.utils.Utils;

import static com.schibstedspain.leku.LocationPickerActivityKt.LATITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.LOCATION_ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LONGITUDE;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding activityUserBinding;
    UserActivityViewModel userActivityViewModel;

GlobalPrefrencies globalPrefrencies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUserBinding=DataBindingUtil.setContentView(this,R.layout.activity_user);
        userActivityViewModel= ViewModelProviders.of(this).get(UserActivityViewModel.class);
        activityUserBinding.setLifecycleOwner(this);
        activityUserBinding.setUserVmodel(userActivityViewModel);
        globalPrefrencies = new GlobalPrefrencies(this);
        Utils.setLocale(this,globalPrefrencies.getLanguage());
        showFragment(new LoginFragment());

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
    public double latitude, longitude;
    static final int PLACE_PICKER_REQUEST = 110;
    public String  address;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("XCXC", requestCode + " ");
        Log.e("XCXC", resultCode + " ");

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                latitude = data.getDoubleExtra(LATITUDE, 0.0);
                Log.e("LATITUDE****", latitude + "");
                longitude = data.getDoubleExtra(LONGITUDE, 0.0);
                Log.e("LONGITUDE****", longitude + "");
                address = data.getStringExtra(LOCATION_ADDRESS);
                Log.e("ADDRESS****", address);
            }
        }
    }

}
