package com.mrerror.parachut.ui.home.orders.detailsorder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Adabters.InvoisAdapter;
import com.mrerror.parachut.Models.DetailsOrder.DetailsOrderModel;
import com.mrerror.parachut.Models.DetailsOrder.OrderProduct;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityDetailsOrderBinding;
import com.mrerror.parachut.ui.cart.CartActivity;
import com.mrerror.parachut.ui.home.MainActivity;

import java.util.ArrayList;
import java.util.Objects;

public class DetailsOrderActivity extends AppCompatActivity  implements InvoisAdapter.sumOffers {

    DetailsOrderViewModel mviewModel;
    ActivityDetailsOrderBinding activityDetailsOrderBinding;
    ArrayList<OrderProduct> orderProductArrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mviewModel = ViewModelProviders.of(this).get(DetailsOrderViewModel.class);
        activityDetailsOrderBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_order);
        activityDetailsOrderBinding.setLifecycleOwner(this);
        activityDetailsOrderBinding.setDetailsOrderVmodel(mviewModel);

        String myid = Objects.requireNonNull(getIntent().getExtras()).getString("myid");
        //  setupdata(myid);
        setupdata(myid);
        activityDetailsOrderBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailsOrderActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupdata(String id_) {

        mviewModel.getOrderDetailss(this, id_);
        mviewModel.detailsOrderModelMutableLiveData.observe(this, new Observer<DetailsOrderModel>() {
            @Override
            public void onChanged(DetailsOrderModel detailsOrderModel) {
                setupDataInfields(detailsOrderModel);
            }
        });
    }

    DetailsOrderModel detailsOrderModel;
    private void setupDataInfields(DetailsOrderModel detailsOrderModel) {
        orderProductArrayList.clear();
        this.detailsOrderModel=detailsOrderModel;
        activityDetailsOrderBinding.addressId.setText(detailsOrderModel.getData().getUser().getAddress() + "");
        activityDetailsOrderBinding.txtCount.setText("عدد المنتجات :" + detailsOrderModel.getData().getOrderProducts().size() + " منتج ");
        activityDetailsOrderBinding.phoneId.setText(detailsOrderModel.getData().getUser().getMobile() + "");
        activityDetailsOrderBinding.txtState.setText(detailsOrderModel.getData().getStatus() + "");
        if(detailsOrderModel.getData().getDelivery()!=null) {
            activityDetailsOrderBinding.nametyar.setText(detailsOrderModel.getData().getDelivery().getName() + " ");
        }else {
            activityDetailsOrderBinding.nametyar.setText("لم يتم استلام الطلب من الطيارين");
        }
        InvoisAdapter invoisAdapter = new InvoisAdapter();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        orderProductArrayList.addAll(detailsOrderModel.getData().getOrderProducts());
        invoisAdapter.setOrderProduct(orderProductArrayList,this);
        activityDetailsOrderBinding.rvItems.setLayoutManager(manager);
        activityDetailsOrderBinding.rvItems.setAdapter(invoisAdapter);
    }



    @Override
    public void getAllpriceOffers(double newPrice) {


        activityDetailsOrderBinding.allprice.setText(newPrice + " جنيه ");
        activityDetailsOrderBinding.cost.setText(detailsOrderModel.getData().getOrderPrice() + " جنيه ");
        Log.e("CD",detailsOrderModel.getData().getFinalPrice()+"");
        Log.e("CD",detailsOrderModel.getData().getOrderPrice()+"");

        activityDetailsOrderBinding.delevPrice.setText(detailsOrderModel.getData().getFinalPrice() - newPrice+ " جنيه ");

    }
}
