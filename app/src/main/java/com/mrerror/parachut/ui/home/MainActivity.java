package com.mrerror.parachut.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityMainBinding;
import com.mrerror.parachut.ui.home.fastorder.FastOrderActivity;
import com.mrerror.parachut.ui.home.homefragment.HomeFragment;
import com.mrerror.parachut.ui.home.humbmenu.HamburgActivity;
import com.mrerror.parachut.ui.home.orders.OrdersFragment;
import com.mrerror.parachut.ui.home.profilefragment.ProfileFragment;
import com.mrerror.parachut.utils.GlobalPrefrencies;

public class MainActivity extends AppCompatActivity {
    Menu menu;
    ActivityMainBinding activityMainBinding;
    GlobalPrefrencies globalPrefrencies;
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainViewModel=ViewModelProviders.of(this).get(MainViewModel.class);

        activityMainBinding.setMainVmodel(mainViewModel);
        activityMainBinding.setLifecycleOwner(this);


        activityMainBinding.btnSendFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFastOrderActivity();
            }
        });
        globalPrefrencies = new GlobalPrefrencies(this);
        Log.e("XSX", globalPrefrencies.getApi_token() + " 00 ");
        setUpNavButtons(0);
        showFragment(new HomeFragment());
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStack();
                }
                showFragment(new HomeFragment());
                return false;
            }
        });
        menu.getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStack();
                }
                gotoFastOrderActivity();
                return false;
            }
        });

        menu.getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStack();
                }
                showFragment(new OrdersFragment());
                return false;
            }
        });
        activityMainBinding.humborgmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityMainBinding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        menu.getItem(4).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStack();
                }
                showFragment(new ProfileFragment());
                return false;
            }
        });
        activityMainBinding.layoutAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityHamburg("1");
            }
        });
        activityMainBinding.layoutContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenActivityHamburg("2");
            }
        });
        activityMainBinding.layoutPricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityHamburg("3");
            }
        });
    }

    private void OpenActivityHamburg(String i) {

        Intent intent = new Intent(this, HamburgActivity.class);
        intent.putExtra("key", i);
        startActivity(intent);
    }

    private void gotoFastOrderActivity() {
        Intent intent = new Intent(this, FastOrderActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


    public void showFragment(Fragment fragment) {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containermain, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
    public void setUpNavButtons(int numFragment) {
        //to change icon for navigation
        menu=activityMainBinding.bottomReact.getMenu();
        MenuItem menuItem = menu.getItem(numFragment);
        menuItem.setChecked(true);
    }

}
