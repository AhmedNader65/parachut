package com.mrerror.parachut.Adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Models.DetailsOrder.OrderProduct;
import com.mrerror.parachut.R;

import java.util.ArrayList;


public class InvoisAdapter extends RecyclerView.Adapter<InvoisAdapter.InvoisNHolder> {

    ArrayList<OrderProduct> orderProductList = new ArrayList<>();

    sumOffers sumOffers;
    public void setOrderProduct(ArrayList<OrderProduct> categories,sumOffers sumOffers) {
        this.orderProductList = categories;
        this.sumOffers=sumOffers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InvoisNHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invois, parent, false);
        return new InvoisNHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoisNHolder holder, int position) {
        OrderProduct orderProduct = orderProductList.get(position);
        holder.textViewName.setText(orderProduct.getProducts().getName() + " (" + orderProduct.getQuantity() + ") ");
        holder.textViewPrice.setText(orderProduct.getProducts().getOffer() + " جنيه ");
        holder.textViewId.setText((position+1) + "");

            double priceItem = (orderProduct.getProducts().getOffer()) * (orderProduct.getQuantity());
        double newPrice = addNewPrice(priceItem);

        sumOffers.getAllpriceOffers(newPrice);
    }

    double allPrice=0;
    private double addNewPrice(double priceItem) {
        allPrice=allPrice+priceItem;

        return allPrice;
    }

    @Override
    public int getItemCount() {
        return orderProductList.size();
    }

    class InvoisNHolder extends RecyclerView.ViewHolder {

        TextView textViewId, textViewName, textViewPrice;

        public InvoisNHolder(@NonNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.id_);
            textViewPrice = itemView.findViewById(R.id.price_);
            textViewName = itemView.findViewById(R.id.name_);


        }

    }
    public  interface sumOffers{
        void  getAllpriceOffers(double newPrice);
    }
}
