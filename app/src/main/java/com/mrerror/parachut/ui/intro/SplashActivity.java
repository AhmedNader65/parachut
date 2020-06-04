package com.mrerror.parachut.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.ui.usercontrol.UserActivity;
import com.mrerror.parachut.utils.GlobalPrefrencies;

public class SplashActivity extends AppCompatActivity {
    Intent intent2;
    int TIME_SPLASH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CheckIntoActivity();
            }
        }, TIME_SPLASH);


    }
    GlobalPrefrencies  globalPrefrencies;
    private void CheckIntoActivity() {
globalPrefrencies=new GlobalPrefrencies(this);
        if(globalPrefrencies.getLoginStatus()){
            intent2 = new Intent(SplashActivity.this, MainActivity.class);
        }else {
            intent2 = new Intent(SplashActivity.this, UserActivity.class);
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                startActivity(intent2);
            }
        });
        t.start();
        finish();
    }
}
