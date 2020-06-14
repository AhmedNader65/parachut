package com.mrerror.parachut.ui.cart;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.mrerror.parachut.Adabters.AdabterCart;
import com.mrerror.parachut.Models.BaseModel;
import com.mrerror.parachut.Models.Datum;
import com.mrerror.parachut.Models.ProductModel.DetailsProductModel;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityCartBinding;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.utils.DeletCartItemInfoInterface;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import java.util.ArrayList;
import java.util.List;


public class CartActivity extends AppCompatActivity implements AdabterCart.onChangeQuantityListener{
    public static List<Datum> dataArrayListProduct = new ArrayList<>();

    ActivityCartBinding activityCartBinding;
    CartViewMmodel cartViewMmodel;
    GlobalPrefrencies globalPrefrencies;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityCartBinding= DataBindingUtil.setContentView(this, R.layout.activity_cart);
        cartViewMmodel= ViewModelProviders.of(this).get(CartViewMmodel.class);
//setupWindowAnimations();
        activityCartBinding.setCartVmodel(cartViewMmodel);
        activityCartBinding.setLifecycleOwner(this);


        activityCartBinding.setCartVmodel(cartViewMmodel);
        activityCartBinding.setLifecycleOwner(this);
        globalPrefrencies=new GlobalPrefrencies(CartActivity.this);
        activityCartBinding.emptyCartTv.setVisibility(View.VISIBLE);
        cartDetails();
        if (dataArrayListProduct.isEmpty()) {

            activityCartBinding.emptyCartTv.setVisibility(View.VISIBLE);
            activityCartBinding.cartShopRv.setVisibility(View.GONE);

        } else {
            activityCartBinding.emptyCartTv.setVisibility(View.GONE);
            activityCartBinding.cartShopRv.setVisibility(View.VISIBLE);
        }

//        activityCartBinding.btnAddtoCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (globalPrefrencies.getLoginStatus()) {
//
//
//                    if (!dataArrayListProduct.isEmpty()) {
//
//
//                        if (globalPrefrencies.getLoginStatus()) {
//
//                            Gson gson = new Gson();
//                            JsonElement element = gson.toJsonTree(dataArrayListProduct, new TypeToken<List<DetailsProductModel>>() {
//                            }.getType());
//
//                            if (!element.isJsonArray()) {
//                                // fail appropriately
//
//                            }
////                        JsonArray jsonArray = element.getAsJsonArray();
////
////                        Intent intent = new Intent(CartActivity.this, DetailsUserActivity.class);
////                        intent.putExtra("vlaPrice", cartPriceTv.getText().toString());
////                        intent.putExtra("jsonArrayOfCart", String.valueOf(jsonArray));
////
////                        startActivity(intent);
////                        Log.e("XCXCXCXC", String.valueOf(jsonArray));
//                        } else {
//                            if (!globalPrefrencies.getLanguage().equals("ar")) {
//
//                                Snackbar snackbar = Snackbar.make(v, "Please Login First", Snackbar.LENGTH_LONG);
//                                snackbar.show();
//                            } else {
//
//                                Snackbar snackbar = Snackbar.make(v, "للمتابعة من فضلك قم بتسجيل الدخول", Snackbar.LENGTH_LONG);
//                                snackbar.show();
//                            }
//
//
//                        }
//
//                    } else {
//
//                        if (!globalPrefrencies.getLanguage().equals("ar")) {
//                            Snackbar snackbar = Snackbar.make(v, "Cart is Empty", Snackbar.LENGTH_LONG);
//                            snackbar.show();
//
//                        } else {
//
//                            Snackbar snackbar = Snackbar.make(v, "عربه الشراء فارغة", Snackbar.LENGTH_LONG);
//                            snackbar.show();
//                        }
//
//
//                    }
//                }else {
//                    Toast.makeText(getContext(), "برجاء تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        activityCartBinding.btnAddtoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (globalPrefrencies.getLoginStatus()) {
                    if (!dataArrayListProduct.isEmpty()) {

                        Gson gson = new Gson();
                        JsonElement element = gson.toJsonTree(dataArrayListProduct, new TypeToken<List<DetailsProductModel>>() {
                        }.getType());

                        if (!element.isJsonArray()) {
                            // fail appropriately

                        }
                        JsonArray jsonArray = element.getAsJsonArray();

                        //Intent intent = new Intent(getContext(), MyPaymentActivity.class);
                        //intent.putExtra("vlaPrice", activityCartBinding.cartPriceTv.getText().toString());
                        //intent.putExtra("jsonArrayOfCart", String.valueOf(jsonArray));
                        //startActivity(intent);

                        Log.e("bnbn", new Gson().toJson(element));
                        sendToBackEnd(String.valueOf(jsonArray));
                    } else {

                        if (!globalPrefrencies.getLanguage().equals("ar")) {
                            Snackbar snackbar = Snackbar.make(v, "Cart is Empty", Snackbar.LENGTH_LONG);
                            snackbar.show();

                        } else {

                            Snackbar snackbar = Snackbar.make(v, "عربه الشراء فارغة", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                }else {
                    Toast.makeText(CartActivity.this, "برجاء تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();
                }

            }
        });

        activityCartBinding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendToBackEnd(String valueOf) {
        cartViewMmodel.sendToDB(valueOf,30.20,30.00,this);
        cartViewMmodel.baseModelMutableLiveData.observe(this, new Observer<BaseModel>() {
        @Override
        public void onChanged(BaseModel baseModel) {
            if(baseModel.getStatus().equals("successful")){
                Toast.makeText(CartActivity.this, "تمت العملية بنجاح", Toast.LENGTH_SHORT).show();

                dataArrayListProduct.clear();
                Intent intent=new Intent(CartActivity.this, MainActivity.class);
                CartActivity.this.startActivity(intent);
                CartActivity.this.finish();
            }
            else {
                Toast.makeText(CartActivity.this, "من فضلك اعد المحاولة", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }

    AdabterCart cartAdapter;

    private void cartDetails() {
        cartAdapter = new AdabterCart(dataArrayListProduct, new DeletCartItemInfoInterface() {
            @Override
            public void onDelet(int i) {
                if (i == 10) {
                    fetchCartInfo();
                    if (dataArrayListProduct.isEmpty()) {
                        activityCartBinding.emptyCartTv.setVisibility(View.VISIBLE);
                    } else {
                        activityCartBinding.emptyCartTv.setVisibility(View.GONE);
                    }
                }
            }
        }, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        activityCartBinding.cartShopRv.setLayoutManager(layoutManager);
        activityCartBinding.cartShopRv.setAdapter(cartAdapter);
        activityCartBinding.count.setText("( "+dataArrayListProduct.size()+" ) ");
        activityCartBinding.totaltext.setText(cartAdapter.getAllPrice()+"");

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
    private void fetchCartInfo() {
        String price;
        int count = 0;


        if (!dataArrayListProduct.isEmpty()) {

            for (int i = 0; i < dataArrayListProduct.size(); i++) {

                price = dataArrayListProduct.get(i).getPrice() + ""; //* CartActivity.shopProductsList.get(i).getProductCount();

                Log.e("price", price);
                activityCartBinding.subPrice.setText(price);
                count += dataArrayListProduct.get(i).getCount();
            }
        } else {
            Log.e("price", String.valueOf(0));
            activityCartBinding.subPrice.setText("");

            if (!globalPrefrencies.getLanguage().equals("ar")) {
                activityCartBinding.subPrice.setText(0 + " EGY");
            } else {
                activityCartBinding.subPrice.setText(0 + " جنيه ");
            }

        }
    }
    @Override
    public void onChange() {
        activityCartBinding.subPrice.setText(cartAdapter.getAllQuantity() + " " + cartAdapter.getAllPrice());
        activityCartBinding.totaltext.setText(" " + (Integer.parseInt(cartAdapter.getAllPrice())) + "");


        if (!globalPrefrencies.getLanguage().equals("ar")) {
            activityCartBinding.totalCurrency.setText(" EGY");
        } else {
            activityCartBinding.totalCurrency.setText(" جنيه ");
        }

        //cartPriceTv.setText(" " + (Integer.parseInt(cartAdapter.getAllPrice().toString())));
    }


}
