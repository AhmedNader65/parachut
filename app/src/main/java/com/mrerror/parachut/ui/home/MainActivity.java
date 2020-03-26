package com.mrerror.parachut.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityMainBinding;
import com.mrerror.parachut.ui.home.fastorder.FastOrderActivity;
import com.mrerror.parachut.ui.home.homefragment.HomeFragment;
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

        menu.getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                gotoFastOrderActivity();
                return false;
            }
        });

    }

    private void gotoFastOrderActivity() {
        Intent intent = new Intent(this, FastOrderActivity.class);
        startActivity(intent);
    }

    public void showFragment(Fragment fragment) {
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
