package com.mrerror.parachut.ui.product;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mrerror.parachut.Adabters.SimilarProductsAdapter;
import com.mrerror.parachut.Models.SimilarProducts.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivitySingleProductBinding;

public class SingleProductActivity extends AppCompatActivity {

    ActivitySingleProductBinding activitySingleProductBinding;
    SingleProductViewModel singleProductViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySingleProductBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_product);
        singleProductViewModel = ViewModelProviders.of(this).get(SingleProductViewModel.class);
        activitySingleProductBinding.setSimilarVmodel(singleProductViewModel);
        activitySingleProductBinding.setLifecycleOwner(this);

        setupSimilarProducts();
    }

    private void setupSimilarProducts()
    {
        final SimilarProductsAdapter adapter = new SimilarProductsAdapter(getApplicationContext());
        activitySingleProductBinding.rvSimilar.setLayoutManager(new LinearLayoutManager(this));
        singleProductViewModel.mutableLiveDataOrdersPageList.observe(this, new Observer<PagedList<com.mrerror.parachut.Models.SimilarProducts.Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {
                adapter.submitList(data);
                Log.e("XXXCXC", data.size() + "");
            }
        });

        activitySingleProductBinding.rvSimilar.setAdapter(adapter);

    }
}
