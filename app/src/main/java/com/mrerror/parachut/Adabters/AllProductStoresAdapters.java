package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.mrerror.parachut.Models.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.product.SingleProductActivity;

public class AllProductStoresAdapters extends PagedListAdapter<Datum, AllProductStoresAdapters.ProductStoresVholder> {

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

    public AllProductStoresAdapters() {
        super(item_COMPARATOR);
    }

    @NonNull
    @Override
    public ProductStoresVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offers,parent,false);
        return new ProductStoresVholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ProductStoresVholder holder, int position) {

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
    }

    class ProductStoresVholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName,textViewPrice,textViewOffer,textViewRatio,textViewnamstore;

        public ProductStoresVholder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.nameitem);
            imageView = itemView.findViewById(R.id.imgitem);
            textViewnamstore=itemView.findViewById(R.id.smitem);
            textViewRatio = itemView.findViewById(R.id.oferratitem);
            textViewOffer = itemView.findViewById(R.id.offersitem);
            textViewPrice= itemView.findViewById(R.id.priceitem);
        }
    }
}
