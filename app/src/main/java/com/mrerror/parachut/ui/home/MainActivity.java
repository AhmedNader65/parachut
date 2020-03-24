package com.mrerror.parachut.ui.home;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityMainBinding;
import com.mrerror.parachut.databinding.ActivityUserBinding;
import com.mrerror.parachut.ui.home.homefragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    Menu menu;
    ActivityMainBinding activityMainBinding;
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setMainVmodel(mainViewModel);
        activityMainBinding.setLifecycleOwner(this);



        setUpNavButtons(0);
        showFragment(new HomeFragment());



    }
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
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
