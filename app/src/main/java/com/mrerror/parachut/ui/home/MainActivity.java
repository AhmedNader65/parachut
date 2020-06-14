package com.mrerror.parachut.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mrerror.parachut.Models.BaseResponce;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityMainBinding;
import com.mrerror.parachut.ui.cart.CartActivity;
import com.mrerror.parachut.ui.home.fastorder.FastOrderActivity;
import com.mrerror.parachut.ui.home.homefragment.HomeFragment;
import com.mrerror.parachut.ui.home.humbmenu.HamburgActivity;
import com.mrerror.parachut.ui.home.notifications.NotificationsFragment;
import com.mrerror.parachut.ui.home.orders.OrdersFragment;
import com.mrerror.parachut.ui.home.profilefragment.ProfileFragment;
import com.mrerror.parachut.ui.usercontrol.UserActivity;
import com.mrerror.parachut.utils.ChangeCountCartInterface;
import com.mrerror.parachut.utils.GlobalPrefrencies;
import com.mrerror.parachut.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.schibstedspain.leku.LocationPickerActivityKt.LATITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.LOCATION_ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LONGITUDE;

public class MainActivity extends AppCompatActivity implements ChangeCountCartInterface {
    public static String user_token;
    Menu menu;
    ActivityMainBinding activityMainBinding;
    GlobalPrefrencies globalPrefrencies;
    MainViewModel mainViewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainViewModel=ViewModelProviders.of(this).get(MainViewModel.class);

        activityMainBinding.setMainVmodel(mainViewModel);
        activityMainBinding.setLifecycleOwner(this);
        String notification = "";
        if (getIntent().getExtras() != null) {
            notification = getIntent().getExtras().getString("notification");
        }
        setupWindowAnimations();
        globalPrefrencies = new GlobalPrefrencies(this);


        Utils.setLocale(MainActivity.this,globalPrefrencies.getLanguage());
        user_token = globalPrefrencies.getApi_token();

        if (notification != null && notification.equals("not")) {
            if (globalPrefrencies.getLoginStatus()) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStack();
                }
                Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                showFragment(new NotificationsFragment());
            }
        }
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }


                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        getApiToken(token);

                        Log.i("deviceTokennnnn", token);


                        // Log and toast
//                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
                    }
                });
        activityMainBinding.btnSendFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFastOrderActivity();
            }
        });

        setUpNavButtons(0);
        showFragment(new HomeFragment());
        activityMainBinding.cartText.setText("0");
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
        activityMainBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
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

        menu.getItem(3).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (globalPrefrencies.getLoginStatus()) {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                        getSupportFragmentManager().popBackStack();
                    }
                    Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                    showFragment(new NotificationsFragment());
                    return false;
                }
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
        activityMainBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalPrefrencies=new GlobalPrefrencies(MainActivity.this);
                globalPrefrencies.storeLoginStatus(false);
                globalPrefrencies.storeApi_token("");

                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                FirebaseMessaging.getInstance().unsubscribeFromTopic("user");

                finish();
            }
        });
        activityMainBinding.layoutPricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivityHamburg("3");
            }
        });


    }


    public void getApiToken(String token) {

        RetroWeb.getClient().create(ServiceApi.class).getDeviceToken(token, globalPrefrencies.getLanguage(), "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<BaseResponce>() {
            @Override
            public void onResponse(Call<BaseResponce> call, Response<BaseResponce> response) {

                Log.e("tokenResponse", response.body().getStatus());

            }

            @Override
            public void onFailure(Call<BaseResponce> call, Throwable t) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }
    ChangeCountCartInterface changeCountCartInterface;
    @Override
    protected void onResume() {
        super.onResume();
        changeCountCartInterface=this;
        onChangeCount();
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

    @Override
    public void onChangeCount() {

        int size = CartActivity.dataArrayListProduct.size();
        if(size>0){
            activityMainBinding.cartText.setText(size+"");
            activityMainBinding.cartText.setVisibility(View.VISIBLE);
        }else {
            activityMainBinding.cartText.setVisibility(View.INVISIBLE);
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
