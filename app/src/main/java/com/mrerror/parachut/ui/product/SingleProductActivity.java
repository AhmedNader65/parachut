package com.mrerror.parachut.ui.product;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.mrerror.parachut.Adabters.SimilarProductsAdapter;
import com.mrerror.parachut.Models.Datum;
import com.mrerror.parachut.Models.ProductModel.DetailsProductModel;
import com.mrerror.parachut.Models.ProductModel.Image;
import com.mrerror.parachut.Models.SimilarProducts.SimilarProductsModel;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ActivitySingleProductBinding;
import com.mrerror.parachut.ui.cart.CartActivity;
import com.mrerror.parachut.utils.ChangeCountCartInterface;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class SingleProductActivity extends AppCompatActivity implements ChangeCountCartInterface {

    ActivitySingleProductBinding activitySingleProductBinding;
    SingleProductViewModel singleProductViewModel;
    ChangeCountCartInterface changeCountCartInterface;

    GlobalPrefrencies globalPrefrencies;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySingleProductBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_product);
        singleProductViewModel = ViewModelProviders.of(this).get(SingleProductViewModel.class);
        activitySingleProductBinding.setSimilarVmodel(singleProductViewModel);
        activitySingleProductBinding.setLifecycleOwner(this);

        globalPrefrencies = new GlobalPrefrencies(this);
        final String id_= Objects.requireNonNull(getIntent().getExtras()).getString("product_id");
        setupData(id_);
        setupSimilarProducts(id_);


        activitySingleProductBinding.idDecreaseProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!activitySingleProductBinding.idQuantiProduct.getText().toString().equals("1") || !activitySingleProductBinding.idQuantiProduct.getText().toString().equals("0")) {
                    String tvQuantityText = activitySingleProductBinding.idQuantiProduct.getText().toString();
                    int quantInt = Integer.parseInt(tvQuantityText);
                    if (quantInt != 1) {
                        quantInt = quantInt - 1;
                    } else {
                        Toast.makeText(SingleProductActivity.this, "لايمكن اجراء هذه العملية ", Toast.LENGTH_SHORT).show();
                    }
                    activitySingleProductBinding.idQuantiProduct.setText(quantInt + "");
//                    setTotalPrice();setTotalPrice
                }
            }
        });
        activitySingleProductBinding.idIncreaseProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvQuantityText = activitySingleProductBinding.idQuantiProduct.getText().toString();
                int quantInt = Integer.parseInt(tvQuantityText);
                quantInt = quantInt + 1;
                activitySingleProductBinding.idQuantiProduct.setText(quantInt + "");
            }
        });


        activitySingleProductBinding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activitySingleProductBinding.btnAddtoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    AddToMyCart(Integer.parseInt(id_),v);
                    if (getFragmentManager().getBackStackEntryCount() > 1) {
                        getFragmentManager().popBackStack();
                    }

            }
        });
        activitySingleProductBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SingleProductActivity.this, CartActivity.class);

                startActivity(intent);
            }
        });
    }

    int i;

    private boolean contains(ArrayList<Datum> dataArrayListProduct, Integer id) {


        for (i = 0; i < dataArrayListProduct.size(); i++) {

            Log.e("*  *" + dataArrayListProduct.get(i).getId() + "*  *", "*" + id + "*   *" + i);
            if (dataArrayListProduct.get(i).getId().toString().equals(id + "")) {

                Log.e("positionsssss", String.valueOf(i));
                Log.e("connnnnn", "yes");

                return true;
            }
        }
        return false;

    }


    private void backPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


    private void setupData(String id_) {

        activitySingleProductBinding.rvSimilar.setLayoutManager(new LinearLayoutManager(this));
        singleProductViewModel.setUpProduct(id_ + "", this);
        singleProductViewModel.ProductsMutableLiveData.observe(this, new Observer<DetailsProductModel>() {
            @Override
            public void onChanged(DetailsProductModel detailsProductModel) {
                item = detailsProductModel.getData();

                setUpDataItem(detailsProductModel);
                setUpModel(detailsProductModel);

            }
        });


    }

    private void setUpModel(DetailsProductModel detailsProductModel) {


        activitySingleProductBinding.txtcatname.setText(detailsProductModel.getData().getCategory().getName() + "");
        activitySingleProductBinding.txtprice.setText(detailsProductModel.getData().getPrice() + "جنيه");
        activitySingleProductBinding.txtoffer.setText(detailsProductModel.getData().getOfferText() + "");
        activitySingleProductBinding.namesm.setText(detailsProductModel.getData().getSupermarket().getName() + "");
        activitySingleProductBinding.txtname.setText(detailsProductModel.getData().getName() + "");

    }

    ArrayList<Datum> data;

    private void setupSimilarProducts(String id_) {
        data = new ArrayList<>();
        final SimilarProductsAdapter adapter = new SimilarProductsAdapter();
        activitySingleProductBinding.rvSimilar.setLayoutManager(new LinearLayoutManager(this));
        singleProductViewModel.setUpSimilarProducts(id_ + "", this);
        singleProductViewModel.allsimilarProductsDataSourceMutableLiveData.observe(this, new Observer<SimilarProductsModel>() {
            @Override
            public void onChanged(SimilarProductsModel similarProductsModel) {
                data.addAll(similarProductsModel.getData());
                adapter.setProducts(data);
            }
        });
        activitySingleProductBinding.rvSimilar.setAdapter(adapter);
    }


    Datum item;
    Timer timer;

    SliderPagerAdapter sliderPagerAdapter;

    public void AddToMyCart(int id_, View v) {

        Snackbar snackbar;
        if (!contains((ArrayList<Datum>) CartActivity.dataArrayListProduct, id_)) {
            int i = Integer.parseInt(activitySingleProductBinding.idQuantiProduct.getText().toString().trim());
            item.setCount(i);
            CartActivity.dataArrayListProduct.add(item);
            Log.e("XXX", "add");
            if(!globalPrefrencies.getLanguage().equals("ar")){


                snackbar = Snackbar.make(v, "Done , Add is Success", Snackbar.LENGTH_LONG);
                snackbar.setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(SingleProductActivity.this, CartActivity.class);
                        SingleProductActivity.this.startActivity(intent);

                    }
                });
            }else {
                snackbar = Snackbar.make(v, "تمت الاضافة الى سلة الشراء بنجاح", Snackbar.LENGTH_LONG);
                snackbar.setAction("الى سله الشراء", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(SingleProductActivity.this, CartActivity.class);
                        startActivity(intent);

                    }
                });

            }
            onChangeCount();

        } else {
            Log.e("XXX", "Noadd");
            //  CartActivity.dataArrayListProduct.get(position-1).setCount(1);
            if(!globalPrefrencies.getLanguage().equals("ar")){


                snackbar = Snackbar.make(v, "Done , Already found", Snackbar.LENGTH_LONG);
                snackbar.setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(SingleProductActivity.this, CartActivity.class);
                        SingleProductActivity.this.startActivity(intent);

                    }
                });
            }else {
                snackbar = Snackbar.make(v, "هذا الطلب موجود بالفعل", Snackbar.LENGTH_LONG);
                snackbar.setAction("الى سله الشراء", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SingleProductActivity.this, CartActivity.class);
                        SingleProductActivity.this.startActivity(intent);

                    }
                });

            }

        }



        View snackView = snackbar.getView();
        snackView.setBackgroundResource(R.drawable.btnclickcolor);
        snackView.setPadding(4, 4, 4, 4);
        snackView.setBackgroundColor(ContextCompat.getColor(SingleProductActivity.this, R.color.white));
        TextView snackViewText = snackView.findViewById(R.id.snackbar_text);
        snackViewText.setTextSize(14);
        snackViewText.setTextColor(ContextCompat.getColor(SingleProductActivity.this, R.color.colorPrimaryDark));
        Button snackViewButton = snackView.findViewById(R.id.snackbar_action);
        snackViewButton.setTextColor(ContextCompat.getColor(SingleProductActivity.this, R.color.colorPrimary));
        snackViewButton.setTextSize(20);
        snackbar.show();


    }


    @Override
    protected void onResume() {
        super.onResume();
        changeCountCartInterface = this;
        onChangeCount();
    }

    ArrayList<Image> listSliderUrl = new ArrayList<>();

    private void setUpDataItem(DetailsProductModel detailsProductModel) {
        listSliderUrl.clear();

        if (detailsProductModel.getImage() != null) {


            listSliderUrl.addAll(detailsProductModel.getImage());


            activitySingleProductBinding.indicator.setViewPager(activitySingleProductBinding.viewPager);


            final int[] ci = {0};

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    activitySingleProductBinding.viewPager.post(new Runnable() {
                        @Override
                        public void run() {

                            Log.e("viewPager", "" + ci[0]);
                            activitySingleProductBinding.viewPager.setCurrentItem(ci[0] % 3);
                            ci[0]++;

                        }
                    });
                }
            }, 1000, 6000);

            sliderPagerAdapter = new SliderPagerAdapter();
            activitySingleProductBinding.viewPager.setAdapter(sliderPagerAdapter);
            activitySingleProductBinding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
            activitySingleProductBinding.indicator.setViewPager(activitySingleProductBinding.viewPager);
            sliderPagerAdapter.notifyDataSetChanged();
        }



    }

    @Override
    public void onChangeCount() {

        int size = CartActivity.dataArrayListProduct.size();
        if(size>0){
            activitySingleProductBinding.cartText.setText(size+"");
            activitySingleProductBinding.cartText.setVisibility(View.VISIBLE);
        }else {
            activitySingleProductBinding.cartText.setVisibility(View.INVISIBLE);
        }
    }

    public class SliderPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;
        public SliderPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_slider_preview, container, false);

            ImageView imageViewPreview = view.findViewById(R.id.sliderIv);


            String linkImageSlider = listSliderUrl.get(position).getImage();
            final Uri uri = Uri.parse(linkImageSlider);
            Log.e("URI SLIDER", uri + "");
            Glide.with(SingleProductActivity.this)
                    .load(uri)
//                    .thumbnail(0.5f)
                    .into(imageViewPreview);


            imageViewPreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent fullScreenIntent = new Intent(SingleProductActivity.this, FullImage.class);
                    fullScreenIntent.setData(uri);
                    startActivity(fullScreenIntent);
                }
            });

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return listSliderUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    //  page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void displayMetaInfo(int position) {
//        lblCount.setText((position + 1) + " / " + images.size());
//
//        MyImages image = images.get(position);
        //lblTitle.setText(image.getName());
        //lblDate.setText(image.getTimestamp());
    }


}

