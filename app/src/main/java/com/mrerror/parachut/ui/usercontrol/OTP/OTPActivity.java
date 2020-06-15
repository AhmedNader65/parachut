package com.mrerror.parachut.ui.usercontrol.OTP;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;
import com.mrerror.parachut.Models.BaseResponce;
import com.mrerror.parachut.Models.Register.Data;
import com.mrerror.parachut.Models.Register.UserRegisterModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.utils.GlobalPrefrencies;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {

    TextView textViewEnterCode, textViewError;
    GlobalPrefrencies globalPrefrencies;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);


        textViewEnterCode = findViewById(R.id.plzEnterCode);
        textViewError = findViewById(R.id.errorCode);

        OtpView otpView;
        globalPrefrencies = new GlobalPrefrencies(this);
        otpView = findViewById(R.id.otp_view);

        final UserRegisterModel code = (UserRegisterModel) getIntent().getExtras().getSerializable("code");
        Toast.makeText(OTPActivity.this, "onOtpCompleted=> " + code.getData().getUser().getVerifyCode() + "", Toast.LENGTH_LONG).show();

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                getCodeFromApi(otp, code.getData().getUser().getVerifyCode() + "", code.getData());
            }
        });
    }

    private void getCodeFromApi(String otp, String code, Data model) {

        if (otp.equals(code)) {

            int id = model.getUser().getId();
            String name = model.getUser().getName();
            String phone = model.getUser().getMobile();
            String api_token = model.getToken();


            Toast.makeText(this, "مرحبا بك " + name, Toast.LENGTH_LONG).show();
            globalPrefrencies.storeLoginStatus(true);
            globalPrefrencies.storeUserId(id);
            globalPrefrencies.storeName(name);
            globalPrefrencies.storePhone(phone);
            globalPrefrencies.storeApi_token(api_token);
            globalPrefrencies.storeLoginStatus(true);

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            FirebaseMessaging.getInstance().subscribeToTopic("user");
            overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
            finish();
        }

    }


    public void getApiToken(String token) {

        RetroWeb.getClient().create(ServiceApi.class).getDeviceToken(token, globalPrefrencies.getLanguage(), "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<BaseResponce>() {
            @Override
            public void onResponse(Call<BaseResponce> call, Response<BaseResponce> response) {

                Log.i("tokenResponse", response.body().getStatus());

            }

            @Override
            public void onFailure(Call<BaseResponce> call, Throwable t) {

            }
        });

    }
}
