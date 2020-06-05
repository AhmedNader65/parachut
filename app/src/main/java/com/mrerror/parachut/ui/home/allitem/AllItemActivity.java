package com.mrerror.parachut.ui.home.allitem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Adabters.AllOffersAdapters;
import com.mrerror.parachut.Adabters.AllProductCategotiesAdapters;
import com.mrerror.parachut.Adabters.AllSuperMarketAdapters;
import com.mrerror.parachut.Models.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityAllItemBinding;
import com.mrerror.parachut.ui.cart.CartActivity;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.utils.ChangeCountCartInterface;

import java.util.ArrayList;
import java.util.List;

public class AllItemActivity extends AppCompatActivity implements OffersBottomSheetDialog.BottomSheetListener , ChangeCountCartInterface {

    ActivityAllItemBinding activityAllItemBinding;
    AllitemViewModel allitemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAllItemBinding= DataBindingUtil.setContentView(this, R.layout.activity_all_item);

        allitemViewModel=new AllitemViewModel(this);
        activityAllItemBinding.setItemVmodel(allitemViewModel);
        activityAllItemBinding.setLifecycleOwner(this);

        activityAllItemBinding.itemsProgress.setVisibility(View.VISIBLE);

        String key = getIntent().getExtras().getString("key");
        if(key.equals("Offers")){
            setUpAllOffers(100);
        }else if (key.equals("Stores")){
            setUpAllStores();
        }else if (key.equals("category")){
            String _id = getIntent().getExtras().getString("_id");
            setUpProductsCategory(100,_id);
        }else if (key.equals("ProductStores")){
            String _id = getIntent().getExtras().getString("_id");
            setUpProductStores(100,_id);
        }

        activityAllItemBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllItemActivity.this, CartActivity.class);

                startActivity(intent);
            }
        });
        activityAllItemBinding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OffersBottomSheetDialog bottomSheet = new OffersBottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });

        activityAllItemBinding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setUpProductStores(int i, String id) {
        final AllProductCategotiesAdapters adapter = new AllProductCategotiesAdapters();
        activityAllItemBinding.items.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        allitemViewModel.checkHowToSetFilterTypeProductStores(i,id);
        if(i ==1){
            allitemViewModel.mutableLiveDataProductStoresPageListMAX.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
                //    activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        }else if(i ==2){
            allitemViewModel.mutableLiveDataProductStoresPageListCOMMON.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
                //    activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        } else if (i ==3) {
            allitemViewModel.mutableLiveDataProductStoresPageListMIN.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
               //     activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        }
        else{
            allitemViewModel.mutableLiveDataProductStoresPageList.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
            //        activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        }
        activityAllItemBinding.items.setAdapter(adapter);




    }
    private void setUpProductsCategory(int i, String _id) {

        final AllProductCategotiesAdapters adapter = new AllProductCategotiesAdapters();
        activityAllItemBinding.items.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        allitemViewModel.checkHowToSetFilterTypeProductCategory(i,_id);
        if(i ==1){
            allitemViewModel.mutableLiveDataProductCategoryPageListMAX.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
                //    activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        }else if(i ==2){
            allitemViewModel.mutableLiveDataProductCategoryPageListCOMMON.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
              //      activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        } else if (i ==3) {
            allitemViewModel.mutableLiveDataProductCategoryPageListMIN.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
             //       activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        }
        else{
            allitemViewModel.mutableLiveDataProductCategoryPageList.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
               //     activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        }
        activityAllItemBinding.items.setAdapter(adapter);



    }
    private void setUpAllStores() {

        final AllSuperMarketAdapters adapter = new AllSuperMarketAdapters();
        activityAllItemBinding.items.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        allitemViewModel.mutableLiveDataSuperMarketPageList.observe(this, new Observer<PagedList<com.mrerror.parachut.Models.AllSuperMarket.Datum>>() {
            @Override
            public void onChanged(PagedList<com.mrerror.parachut.Models.AllSuperMarket.Datum> data) {
                adapter.submitList(data);
             //   activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
            }
        });
        activityAllItemBinding.items.setAdapter(adapter);

    }
    public void setUpAllOffers(int i) {
        final AllOffersAdapters adapter = new AllOffersAdapters();
        activityAllItemBinding.items.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

       allitemViewModel.checkHowToSetFilterType(i);
        if(i ==1){
            allitemViewModel.mutableLiveDataOffersPageListMAX.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
              //      activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        }else if(i ==2){
            allitemViewModel.mutableLiveDataOffersPageListCOMMON.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
                  //  activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        } else if (i ==3) {
                    allitemViewModel.mutableLiveDataOffersPageListMIN.observe(this, new Observer<PagedList<Datum>>() {
                        @Override
                        public void onChanged(PagedList<Datum> data) {
                            adapter.submitList(data);
                       //     activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                        }
                    });
        }
        else{
            allitemViewModel.mutableLiveDataOffersPageList.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
                   // activityAllItemBinding.itemsProgress.setVisibility(View.GONE);
                }
            });
        }
        activityAllItemBinding.items.setAdapter(adapter);
    }
    @Override
    public void onButtonClicked(String text) {

    }

    ChangeCountCartInterface changeCountCartInterface;
    @Override
    protected void onResume() {
        super.onResume();
        changeCountCartInterface=this;
        onChangeCount();
    }

    @Override
    public void onChangeCount() {

        int size = CartActivity.dataArrayListProduct.size();
        if(size>0){
            activityAllItemBinding.cartText.setText(size+"");
            activityAllItemBinding.cartText.setVisibility(View.VISIBLE);
        }else {
            activityAllItemBinding.cartText.setVisibility(View.INVISIBLE);
            activityAllItemBinding.itemsProgress.setVisibility(View.INVISIBLE);
        }
    }
}
