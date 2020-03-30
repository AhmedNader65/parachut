package com.mrerror.parachut.ui.home.humbmenu;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.home.humbmenu.aboutus.AboutUsFragment;
import com.mrerror.parachut.ui.home.humbmenu.contact.ContactUsFragment;
import com.mrerror.parachut.ui.home.humbmenu.pricing.PricingFragment;

import java.util.Objects;

public class HamburgActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburg);
        String key = Objects.requireNonNull(getIntent().getExtras()).getString("key");

        if (key.equals("1")) {
            showFragment(new AboutUsFragment());
        } else if (key.equals("2")) {
            showFragment(new ContactUsFragment());
        } else if (key.equals("3")) {
            showFragment(new PricingFragment());
        }

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
        ft.replace(R.id.containerhumborg, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

}
