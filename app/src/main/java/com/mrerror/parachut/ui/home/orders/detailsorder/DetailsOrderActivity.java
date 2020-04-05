package com.mrerror.parachut.ui.home.orders.detailsorder;

import android.os.Build;
import android.os.Bundle;

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

import java.util.ArrayList;
import java.util.Objects;

public class DetailsOrderActivity extends AppCompatActivity {

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
        setupdata("32");
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

    private void setupDataInfields(DetailsOrderModel detailsOrderModel) {
        orderProductArrayList.clear();
        activityDetailsOrderBinding.addressId.setText(detailsOrderModel.getData().getUser().getAddress() + "");
        activityDetailsOrderBinding.allprice.setText(detailsOrderModel.getData().getFinalPrice() + " جنيه ");
        activityDetailsOrderBinding.delevPrice.setText(detailsOrderModel.getData().getOrderPrice() + " جنيه ");
        activityDetailsOrderBinding.cost.setText(detailsOrderModel.getData().getFinalPrice() - detailsOrderModel.getData().getOrderPrice() + " جنيه ");
        activityDetailsOrderBinding.txtCount.setText("عدد المنتجات :" + detailsOrderModel.getData().getOrderProducts().size() + " منتج ");
        activityDetailsOrderBinding.phoneId.setText(detailsOrderModel.getData().getUser().getMobile() + "");
        activityDetailsOrderBinding.txtState.setText(detailsOrderModel.getData().getStatus() + "");
        activityDetailsOrderBinding.nametyar.setText(detailsOrderModel.getData().getDelivery().getName() + "");
        InvoisAdapter invoisAdapter = new InvoisAdapter();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        orderProductArrayList.addAll(detailsOrderModel.getData().getOrderProducts());
        invoisAdapter.setOrderProduct(orderProductArrayList);
        activityDetailsOrderBinding.rvItems.setLayoutManager(manager);
        activityDetailsOrderBinding.rvItems.setAdapter(invoisAdapter);


    }

}
