package com.mrerror.parachut.Adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrerror.parachut.Models.Datum;
import com.mrerror.parachut.Models.ProductModel.DetailsProductModel;
import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.cart.CartActivity;
import com.mrerror.parachut.utils.DeletCartItemInfoInterface;

import java.util.List;


public class AdabterCart extends RecyclerView.Adapter<AdabterCart.ViewHolder>  {


    List<Datum> dataListProduct;
    DeletCartItemInfoInterface deletCartItemInfoInterface;
    onChangeQuantityListener onChangeQuantityListener;
    int delet=10;

    public AdabterCart( List<Datum> dataListProduct, DeletCartItemInfoInterface deletCartItemInfoInterface, onChangeQuantityListener onChangeQuantityListener) {

        this.dataListProduct = dataListProduct;
        this.deletCartItemInfoInterface=deletCartItemInfoInterface;
        this.onChangeQuantityListener=onChangeQuantityListener;
    }

    @NonNull
    @Override
    public AdabterCart.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_shop_card, viewGroup, false);

        return new AdabterCart.ViewHolder(itemView);
    }
    Datum data;
    @Override
    public void onBindViewHolder(@NonNull final AdabterCart.ViewHolder holder, final int i) {



        data = dataListProduct.get(i);


        int actPrice=Integer.parseInt(data.getPrice()+"");
        holder.totalPrice.setText(data.getPrice()+"");
        holder.tv_Quantity.setText(data.getCount()+"");

        Glide.with(holder.itemView.getContext())
                .load(data.getImage())
                .into(holder.img_cart);


        holder.tv_name.setText(data.getName()+"");

        holder.img_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!holder.tv_Quantity.getText().toString().equals("1") || !holder.tv_Quantity.getText().toString().equals("0")) {
                    String tvQuantityText = holder.tv_Quantity.getText().toString();
                    int quantInt = Integer.parseInt(tvQuantityText);
                    if (quantInt != 1) {
                        quantInt = quantInt - 1;

                    } else {
                        Toast.makeText(holder.itemView.getContext(), "لايمكن اجراء هذه العملية ", Toast.LENGTH_SHORT).show();
                    }
                    dataListProduct.get(i).setCount(quantInt);
                    holder.tv_Quantity.setText(quantInt + "");
                    notifyDataSetChanged();
                    onChangeQuantityListener.onChange();
                }
            }
        });

        holder.img_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvQuantityText = holder.tv_Quantity.getText().toString();
                int quantInt = Integer.parseInt(tvQuantityText);
                quantInt = quantInt + 1;
                holder.tv_Quantity.setText(quantInt + "");
                dataListProduct.get(i).setCount(quantInt);
                notifyDataSetChanged();
                onChangeQuantityListener.onChange();
            }
        });

        holder.deletIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataListProduct.remove(i);
                notifyDataSetChanged();
                deletCartItemInfoInterface.onDelet(delet);
                onChangeQuantityListener.onChange();

            }
        });
    }







    @Override
    public int getItemCount() {
        return dataListProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView deletIv;
        ImageView img_cart;
        TextView img_decrease,img_increase,sizeTv;
        TextView totalPrice,tv_Quantity,tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.nameitem);
            sizeTv=itemView.findViewById(R.id.sizetv);
            deletIv=itemView.findViewById(R.id.img_delet);
            img_cart=itemView.findViewById(R.id.img_cart);
            totalPrice=itemView.findViewById(R.id.totalPriceId);
            tv_Quantity = itemView.findViewById(R.id.id_quanti_product);
            img_decrease = itemView.findViewById(R.id.id_decrease_product);
            img_increase = itemView.findViewById(R.id.id_increase_product);
        }
    }

    public String getAllPrice(){
        int countProduct=0 ;
        int priceProdutt=0;
        int totalPrice = 0;
        for (int i = 0; i<dataListProduct.size(); i++)
        {
            if(dataListProduct.get(i).getCount()==null) {
                dataListProduct.get(i).setCount(1);
                countProduct = Integer.parseInt(dataListProduct.get(i).getCount().toString());
                priceProdutt = Integer.parseInt(dataListProduct.get(i).getPrice() + "");
                totalPrice += (countProduct * priceProdutt);
            }else {
                countProduct = Integer.parseInt(dataListProduct.get(i).getCount().toString());
                priceProdutt = Integer.parseInt(dataListProduct.get(i).getPrice() + "");
                totalPrice += (countProduct * priceProdutt);
            }
        }

        return totalPrice+"";
    }

    public String getAllQuantity(){
        int totalQuant= 0;
        for (int i = 0; i<dataListProduct.size(); i++) {
            if (dataListProduct.get(i).getCount() == null) {
                dataListProduct.get(i).setCount(1);
                totalQuant += (Integer.parseInt(dataListProduct.get(i).getCount().toString()));
            }else {
                totalQuant += (Integer.parseInt(dataListProduct.get(i).getCount().toString()));
            }
        }

        return  "( "+totalQuant+" items ): ";
    }



    public interface onChangeQuantityListener{
        void onChange();
    }
}
