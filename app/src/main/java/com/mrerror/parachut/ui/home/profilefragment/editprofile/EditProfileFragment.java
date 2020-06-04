package com.mrerror.parachut.ui.home.profilefragment.editprofile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.mrerror.parachut.Models.UserData.GetUserData;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.EditProfileFragmentBinding;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.ui.home.map.MapsActivity;
import com.mrerror.parachut.ui.home.profilefragment.ProfileFragment;
import com.mrerror.parachut.utils.GlobalPrefrencies;
import com.mrerror.parachut.utils.Utils;
import com.schibstedspain.leku.LocationPickerActivity;

import java.util.Objects;

import static com.mrerror.parachut.utils.Utils.LOCATION_PREFS;

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
        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(),globalPrefrencies.getLanguage());
        return editProfileFragmentBinding.getRoot();
    }
    GlobalPrefrencies globalPrefrencies;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);

        editProfileFragmentBinding.setProfileVmodel(mViewModel);
        editProfileFragmentBinding.setLifecycleOwner(this);


        mViewModel.onGetUserData(getContext());

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        mViewModel.muserDataMutableLiveData.observe(getViewLifecycleOwner(), new Observer<GetUserData>() {
            @Override
            public void onChanged(GetUserData getUserData) {

                Log.e("XSX", new Gson().toJson(getUserData));
                editProfileFragmentBinding.nameId.setText(getUserData.getData().getName() + "");
                editProfileFragmentBinding.phoneId.setText(getUserData.getData().getMobile() + "");
                editProfileFragmentBinding.addressId.setText(getUserData.getData().getAddress() + "");

            }
        });
        editProfileFragmentBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
                showFragment(new ProfileFragment());
            }
        });

        editProfileFragmentBinding.laymap.setOnClickListener(new View.OnClickListener() {
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
    }


    private void Update() {

        mViewModel.onClickUpdate(
                editProfileFragmentBinding.nameId.getText().toString().trim(),
                editProfileFragmentBinding.addressId.getText().toString().trim(),
                editProfileFragmentBinding.phoneId.getText().toString(),
                editProfileFragmentBinding.passId.getText().toString().trim(),
                "",
                latitude,longitude,
                getContext()
        );
    }
    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.containerprofile, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    String latitude ,longitude;
    static final int PLACE_PICKER_REQUEST = 110;
    int LOC_REQ_CODE = 1111;
    String address;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity) Objects.requireNonNull(getContext())).address != null) {
            address = ((MainActivity) Objects.requireNonNull(getContext())).address;
            editProfileFragmentBinding.addressId.setText(address);

            latitude=((MainActivity) Objects.requireNonNull(getContext())).latitude+"";
            longitude=((MainActivity) Objects.requireNonNull(getContext())).longitude+"";


        }
        Log.e("XCZZXZZX", editProfileFragmentBinding.addressId.getText().toString() + " ");

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void requestLocationAccessPermission() {
        ActivityCompat.requestPermissions((MainActivity) Objects.requireNonNull(getContext()),
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
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

}
