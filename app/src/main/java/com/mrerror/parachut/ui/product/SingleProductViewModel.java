package com.mrerror.parachut.ui.product;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrerror.parachut.Models.ProductModel.DetailsProductModel;
import com.mrerror.parachut.Models.SimilarProducts.SimilarProductsModel;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.utils.GlobalPrefrencies;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductViewModel extends ViewModel {

    GlobalPrefrencies globalPrefrencies;

    MutableLiveData<SimilarProductsModel> allsimilarProductsDataSourceMutableLiveData=new MutableLiveData<>();
    public void setUpSimilarProducts(String id_, Context  context){
        globalPrefrencies=new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetSimilarProductsModel(id_,globalPrefrencies.getLanguage()).enqueue(new Callback<SimilarProductsModel>() {
            @Override
            public void onResponse(Call<SimilarProductsModel> call, Response<SimilarProductsModel> response) {
                SimilarProductsModel model = response.body();
                if (model != null) {
                    allsimilarProductsDataSourceMutableLiveData.setValue(model);
                }
            }

            @Override
            public void onFailure(Call<SimilarProductsModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }



    MutableLiveData<DetailsProductModel> ProductsMutableLiveData=new MutableLiveData<>();
    public void setUpProduct(String id_, Context context){
        globalPrefrencies=new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetProductsModel(id_,globalPrefrencies.getLanguage()).enqueue(new Callback<DetailsProductModel>() {
            @Override
            public void onResponse(Call<DetailsProductModel> call, Response<DetailsProductModel> response) {
                DetailsProductModel model = response.body();
                if (model != null) {
                    ProductsMutableLiveData.setValue(model);
                }
            }

            @Override
            public void onFailure(Call<DetailsProductModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }


}
