package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrerror.parachut.Models.Datum;

import com.mrerror.parachut.Models.SimilarProducts.SimilarProductsModel;
import com.mrerror.parachut.R;

import java.util.ArrayList;
import java.util.List;

public class SimilarProductsAdapter extends RecyclerView.Adapter<SimilarProductsAdapter.SimilarVmodel> {

    ArrayList<Datum> similarProductsModels = new ArrayList<>();

    public void setProducts(ArrayList<Datum> similarProductsModels) {
        this.similarProductsModels = similarProductsModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SimilarProductsAdapter.SimilarVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similar_products,parent,false);
        return new SimilarProductsAdapter.SimilarVmodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarProductsAdapter.SimilarVmodel holder, int position) {
        Datum datum = similarProductsModels.get(position);
        holder.textViewName.setText(datum.getName() + "");
        holder.textViewnamstore.setText(datum.getDescription() + "");
        holder.textViewPrice.setText(datum.getPrice() + " جنيه ");
        Glide.with(holder.itemView.getContext()).load(datum.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return similarProductsModels.size();
    }

    public class SimilarVmodel extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName,textViewPrice,textViewnamstore;
        public SimilarVmodel(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.nameitem);
            imageView = itemView.findViewById(R.id.imgitem);
            textViewnamstore=itemView.findViewById(R.id.smitem);
            textViewPrice= itemView.findViewById(R.id.item_price);
        }
    }
}
