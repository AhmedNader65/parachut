package com.mrerror.parachut.ui.home.fastorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mrerror.parachut.Models.FastOrder.FastOrderModel;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityFastOrderBinding;
import com.mrerror.parachut.ui.cart.CartActivity;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.utils.Utils;

public class FastOrderActivity extends AppCompatActivity {

    ActivityFastOrderBinding activityFastOrderBinding;
    FastOrderViewModel fastOrderViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityFastOrderBinding = DataBindingUtil.setContentView(this, R.layout.activity_fast_order);
        fastOrderViewModel = ViewModelProviders.of(this).get(FastOrderViewModel.class);
        activityFastOrderBinding.setFastVmodel(fastOrderViewModel);
        activityFastOrderBinding.setLifecycleOwner(this);


        activityFastOrderBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onCheackValidation()) {
                    setUporder();
                }

            }
        });
        activityFastOrderBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FastOrderActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        activityFastOrderBinding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activityFastOrderBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FastOrderActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setUporder() {

        fastOrderViewModel.onSendFastOrder(FastOrderActivity.this,
                activityFastOrderBinding.addrerss.getText().toString().trim(),
                activityFastOrderBinding.phone.getText().toString().trim(),
                activityFastOrderBinding.addrerss.getText().toString().trim(), "30", "30"
        );
        fastOrderViewModel.fastOrderModelMutableLiveData.observe(this, new Observer<FastOrderModel>() {
            @Override
            public void onChanged(FastOrderModel fastOrderModel) {

                openDoneActivity();
            }
        });
    }

    private void openDoneActivity() {
        Intent intent = new Intent(this, GotoHomeActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean onCheackValidation() {

        if (!ValidateAddress()) {
            return false;
        }
        if (!ValidatePhone()) {
            return false;
        }
        return ValidateOrder();
    }


    private boolean ValidateAddress() {
        if (activityFastOrderBinding.addrerss.getText().toString().trim().isEmpty()) {
            activityFastOrderBinding.addrerss.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(activityFastOrderBinding.addrerss, getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidatePhone() {
        if (activityFastOrderBinding.phone.getText().toString().trim().isEmpty()) {
            activityFastOrderBinding.phone.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(activityFastOrderBinding.phone, getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidateOrder() {
        if (activityFastOrderBinding.order.getText().toString().trim().isEmpty()) {
            activityFastOrderBinding.order.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(activityFastOrderBinding.order, getWindow());
            return false;
        }
        return true;
    }
}
