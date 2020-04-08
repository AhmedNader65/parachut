package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrerror.parachut.Models.SimilarProducts.Datum;
import com.mrerror.parachut.R;

public class SimilarProductsAdapter extends PagedListAdapter<Datum , SimilarProductsAdapter.SimilarVmodel> {
    private Context context;
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
    public SimilarProductsAdapter(Context context) {
        super(item_COMPARATOR);
        this.context = context;
    }

    @NonNull
    @Override
    public SimilarProductsAdapter.SimilarVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similar_products,parent,false);
        return new SimilarProductsAdapter.SimilarVmodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarProductsAdapter.SimilarVmodel holder, int position) {
        Datum item = getItem(position);
        holder.textViewName.setText(item.getName() + "");
        holder.textViewnamstore.setText(item.getDescription() + "");
        holder.textViewPrice.setText(item.getPrice() + " جنيه ");
        Glide.with(holder.itemView.getContext()).load(item.getImage()).into(holder.imageView);

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
