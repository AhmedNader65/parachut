package com.mrerror.parachut.ui.home.allitem;

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
import com.mrerror.parachut.Adabters.AllSuperMarketAdapters;
import com.mrerror.parachut.Models.AllOffers.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityAllItemBinding;

public class AllItemActivity extends AppCompatActivity implements OffersBottomSheetDialog.BottomSheetListener {

    ActivityAllItemBinding activityAllItemBinding;
    AllitemViewModel allitemViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAllItemBinding= DataBindingUtil.setContentView(this, R.layout.activity_all_item);
        allitemViewModel= ViewModelProviders.of(this).get(AllitemViewModel.class);

        activityAllItemBinding.setItemVmodel(allitemViewModel);
        activityAllItemBinding.setLifecycleOwner(this);

        String key = getIntent().getExtras().getString("key");
        if(key.equals("Offers")){
            setUpAllOffers(100);
        }else if (key.equals("Stores")){
            setUpAllStores();
        }

        activityAllItemBinding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OffersBottomSheetDialog bottomSheet = new OffersBottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });


    }

    private void setUpAllStores() {


        final AllSuperMarketAdapters adapter = new AllSuperMarketAdapters();
        activityAllItemBinding.items.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        allitemViewModel.mutableLiveDataSuperMarketPageList.observe(this, new Observer<PagedList<com.mrerror.parachut.Models.AllSuperMarket.Datum>>() {
            @Override
            public void onChanged(PagedList<com.mrerror.parachut.Models.AllSuperMarket.Datum> data) {
                adapter.submitList(data);
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
                }
            });
        }else if(i ==2){
            allitemViewModel.mutableLiveDataOffersPageListCOMMON.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
                }
            });
        } else if (i ==3) {
                    allitemViewModel.mutableLiveDataOffersPageListMIN.observe(this, new Observer<PagedList<Datum>>() {
                        @Override
                        public void onChanged(PagedList<Datum> data) {
                            adapter.submitList(data);
                        }
                    });
        }
        else{
            allitemViewModel.mutableLiveDataOffersPageList.observe(this, new Observer<PagedList<Datum>>() {
                @Override
                public void onChanged(PagedList<Datum> data) {
                    adapter.submitList(data);
                }
            });
        }
        activityAllItemBinding.items.setAdapter(adapter);
    }

    @Override
    public void onButtonClicked(String text) {

    }
}
