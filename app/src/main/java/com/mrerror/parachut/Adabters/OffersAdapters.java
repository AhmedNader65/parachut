package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.google.android.material.snackbar.Snackbar;
import com.mrerror.parachut.Models.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.cart.CartActivity;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.ui.product.SingleProductActivity;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import java.util.ArrayList;

public class OffersAdapters extends PagedListAdapter<Datum, OffersAdapters.OffersVholder> {

    private static final DiffUtil.ItemCallback<Datum> item_COMPARATOR = new DiffUtil.ItemCallback<Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return oldItem == newItem;
        }
    };

    public OffersAdapters() {
        super(item_COMPARATOR);
    }

    @NonNull
    @Override
    public OffersVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offers,parent,false);
        return new OffersVholder(view);

    }
GlobalPrefrencies globalPrefrencies;
    @Override
    public void onBindViewHolder(@NonNull final OffersVholder holder, final int position) {
        context=holder.itemView.getContext();
        globalPrefrencies=new GlobalPrefrencies(context);
        final Datum item = getItem(position);
        holder.textViewnamstore.setText(item.getDescription()+"");
        holder.textViewName.setText(item.getName()+"");
        holder.textViewRatio.setText(item.getOfferText()+"");
        holder.textViewOffer.setText(item.getOffer()+" بدلا من ");
        holder.textViewPrice.setText(item.getPrice()+" ج ");
        Glide.with(holder.itemView.getContext()).load(item.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), SingleProductActivity.class);
                intent.putExtra("product_id",item.getId()+"");
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.buttonAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddToMyCart(item.getId(),item,holder,position);
            }
        });


    }
    Context context;
    public void AddToMyCart(int id_, Datum item, OffersVholder holder, int position) {


//        if (contains((ArrayList<Datum>) CartActivity.dataArrayListProduct, id_) == false) {
//
//            CartActivity.dataArrayListProduct.add(item);
//            Toast.makeText(holder.itemView.getContext(), "تمت الاضافة الي سلة الشراء", Toast.LENGTH_SHORT).show();
//
//        } else {
//            Toast.makeText(holder.itemView.getContext(), "هذا الطلب موجود بالفعل", Toast.LENGTH_SHORT).show();
//
//            Log.e("XXX", "Noadd");
//
//
//        }
        Snackbar snackbar;
        if (!contains((ArrayList<Datum>) CartActivity.dataArrayListProduct, id_)) {
            CartActivity.dataArrayListProduct.add(item);
            Log.e("XXX", "add");
            if(!globalPrefrencies.getLanguage().equals("ar")){


                snackbar = Snackbar.make(holder.itemView, "Done , Add is Success", Snackbar.LENGTH_LONG);
                snackbar.setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, CartActivity.class);
                        context.startActivity(intent);

                    }
                });
            }else {
                snackbar = Snackbar.make(holder.itemView, "تمت الاضافة الى سلة الشراء بنجاح", Snackbar.LENGTH_LONG);
                snackbar.setAction("الى سله الشراء", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, CartActivity.class);
                        context.startActivity(intent);

                    }
                });

            }
            ((MainActivity)holder.itemView.getContext()).onChangeCount();

        } else {
            Log.e("XXX", "Noadd");
          //  CartActivity.dataArrayListProduct.get(position-1).setCount(1);
            if(!globalPrefrencies.getLanguage().equals("ar")){


                snackbar = Snackbar.make(holder.itemView, "Done , Already found", Snackbar.LENGTH_LONG);
                snackbar.setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, CartActivity.class);
                        context.startActivity(intent);

                    }
                });
            }else {
                snackbar = Snackbar.make(holder.itemView, "هذا الطلب موجود بالفعل", Snackbar.LENGTH_LONG);
                snackbar.setAction("الى سله الشراء", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CartActivity.class);
                        context.startActivity(intent);

                    }
                });

            }

        }



        View snackView = snackbar.getView();
        snackView.setBackgroundResource(R.drawable.btnclickcolor);
        snackView.setPadding(4, 4, 4, 4);
        snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        TextView snackViewText = (TextView) snackView.findViewById(R.id.snackbar_text);
        snackViewText.setTextSize(14);
        snackViewText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        Button snackViewButton = (Button) snackView.findViewById(R.id.snackbar_action);
        snackViewButton.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        snackViewButton.setTextSize(20);
        snackbar.show();
    }

    int i;

    private boolean contains(ArrayList<Datum> dataArrayListProduct, Integer id) {


        for (i = 0; i < dataArrayListProduct.size(); i++) {

            Log.e("*  *"+dataArrayListProduct.get(i).getId()+"*  *","*"+id+"*   *"+i);
            if (dataArrayListProduct.get(i).getId().toString().equals(id+"")) {

                Log.e("positionsssss", String.valueOf(i));
                Log.e("connnnnn", "yes");

                return true;
            }
        }
        return false;

    }


    class OffersVholder extends RecyclerView.ViewHolder {

        Button buttonAddtoCart;
        ImageView imageView;
        TextView textViewName,textViewPrice,textViewOffer,textViewRatio,textViewnamstore;

        public OffersVholder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.nameitem);
            imageView = itemView.findViewById(R.id.imgitem);
            textViewnamstore=itemView.findViewById(R.id.smitem);
            textViewRatio = itemView.findViewById(R.id.oferratitem);
            textViewOffer = itemView.findViewById(R.id.offersitem);
            textViewPrice= itemView.findViewById(R.id.priceitem);
            buttonAddtoCart=itemView.findViewById(R.id.plus);

        }
    }
}
