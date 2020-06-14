package com.mrerror.parachut.ui.usercontrol.register;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.RegisterFragmentBinding;
import com.mrerror.parachut.ui.usercontrol.UserActivity;
import com.mrerror.parachut.ui.usercontrol.login.LoginFragment;
import com.mrerror.parachut.utils.GlobalPrefrencies;
import com.mrerror.parachut.utils.Utils;
import com.schibstedspain.leku.LocationPickerActivity;

import java.util.Objects;

import static com.mrerror.parachut.utils.Utils.LOCATION_PREFS;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
GlobalPrefrencies globalPrefrencies;
    RegisterFragmentBinding registerFragmentBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        registerFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.register_fragment, container, false);

        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(),globalPrefrencies.getLanguage());
        return registerFragmentBinding.getRoot();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void requestLocationAccessPermission() {
        ActivityCompat.requestPermissions((UserActivity) Objects.requireNonNull(getContext()),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOC_REQ_CODE);
    }

    FusedLocationProviderClient mFusedLocationClient;

    public void pickLocation() {
        SharedPreferences preferences = getContext().getSharedPreferences(LOCATION_PREFS, Context.MODE_PRIVATE);

        Intent locationPickerIntent = new LocationPickerActivity.Builder()
                .withLocation(preferences != null && preferences.getString("Lat", "0") != null ? new LatLng(Double.parseDouble(preferences.getString("Lat", "0")), Double.parseDouble(preferences.getString("Lon", "0"))) : new LatLng(0, 0))
                .withGeolocApiKey(getContext().getString(R.string.google_maps_key))
                .withSearchZone("ar_AR")
                .withGooglePlacesEnabled()
                .withDefaultLocaleSearchZone()
                .shouldReturnOkOnBackPressed()
                .withStreetHidden()
                .withCityHidden()
                .withZipCodeHidden()
                .withSatelliteViewHidden()
                .withGooglePlacesEnabled()
                .withGoogleTimeZoneEnabled()
                .withVoiceSearchHidden()
                .withUnnamedRoadHidden()
                .build(getContext());

        try {
            ((AppCompatActivity) getContext()).startActivityForResult(locationPickerIntent, PLACE_PICKER_REQUEST);
        } catch (Exception e) {
            Log.e("EXIPTIONS", e.getMessage());
        }
    }

    private boolean isLocationAccessPermitted() {
        return ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
        registerFragmentBinding.setRegisterVmodel(mViewModel);
        registerFragmentBinding.setLifecycleOwner(this);



        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        registerFragmentBinding.RegisterId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCheackValidation()) {

                    if(!TextUtils.isEmpty(address)){
                        mViewModel.onClickRegister(
                                registerFragmentBinding.nameId.getText().toString().trim(),
                                registerFragmentBinding.phoneId.getText().toString(),
                                registerFragmentBinding.addressId.getText().toString().trim(),
                                registerFragmentBinding.passId.getText().toString().trim(),
                                latitude,longitude,
                                registerFragmentBinding.conpassid.getText().toString().trim()
                                ,getContext());
                    }else {
                        Toast.makeText(getContext(), "قم باختيار العنوان اولا", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerFragmentBinding.layaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (isLocationAccessPermitted()) {

                        //GET   LOCATION HERE


                        // gotoMap();
                        pickLocation();

                    } else {
                        requestLocationAccessPermission();
                    }
                } else {


                    pickLocation();
                    //gotoMap();

                    //GET   LOCATION HERE

                }
            }
        });

        registerFragmentBinding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPressed();
            }
        });
        registerFragmentBinding.newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                }
                showFragment(new LoginFragment());
            }
        });
    }

    String latitude ,longitude;
    static final int PLACE_PICKER_REQUEST = 110;
    int LOC_REQ_CODE = 1111;

    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
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
        return ValidateEmail();
    }



            String address;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        if (((UserActivity) Objects.requireNonNull(getContext())).address != null) {

            address = ((UserActivity) Objects.requireNonNull(getContext())).address;
            registerFragmentBinding.addressId.setText(address);
            latitude=((UserActivity) Objects.requireNonNull(getContext())).latitude+"";
            longitude=((UserActivity) Objects.requireNonNull(getContext())).longitude+"";

        }
        Log.e("XCZZXZZX", registerFragmentBinding.addressId.getText().toString() + " ");

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

    private void backPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            ((UserActivity)getContext()).finish();
        }
    }


}
