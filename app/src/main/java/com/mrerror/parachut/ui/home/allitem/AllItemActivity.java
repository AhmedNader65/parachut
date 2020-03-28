package com.mrerror.parachut.ui.home.allitem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mrerror.parachut.Adabters.AllOffersAdapters;
import com.mrerror.parachut.Adabters.AllSuperMarketAdapters;
import com.mrerror.parachut.Models.AllOffers.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivityAllItemBinding;

public class AllItemActivity extends AppCompatActivity {

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
            setUpAllOffers();
        }else if (key.equals("Stores")){
            setUpAllStores();
        }


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

    private void setUpAllOffers() {
        final AllOffersAdapters adapter = new AllOffersAdapters();
        activityAllItemBinding.items.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        allitemViewModel.mutableLiveDataOffersPageList.observe(this, new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {
                adapter.submitList(data);
            }
        });
        activityAllItemBinding.items.setAdapter(adapter);
    }
}
