package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Models.Pricing.Datum;
import com.mrerror.parachut.R;

public class PricingAdapter extends PagedListAdapter<Datum , PricingAdapter.PricingVHolder> {

    private static final DiffUtil.ItemCallback<com.mrerror.parachut.Models.Pricing.Datum> item_COMPARATOR = new DiffUtil.ItemCallback<com.mrerror.parachut.Models.Pricing.Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull com.mrerror.parachut.Models.Pricing.Datum oldItem, @NonNull com.mrerror.parachut.Models.Pricing.Datum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull com.mrerror.parachut.Models.Pricing.Datum oldItem, @NonNull com.mrerror.parachut.Models.Pricing.Datum newItem) {
            return oldItem == newItem;
        }
    };

    public PricingAdapter() {
        super(item_COMPARATOR);
    }

    @NonNull
    @Override
    public PricingVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pricing, parent, false);
        return new PricingVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PricingVHolder holder, int position) {

        Datum item = getItem(position);
        holder.tv_location.setText(item.getPlace() + "");
        holder.tv_price.setText(item.getDeliveryPrice() + " جنيه ");

    }

    public class PricingVHolder extends RecyclerView.ViewHolder {

        TextView tv_price, tv_location;

        public PricingVHolder(@NonNull View itemView) {
            super(itemView);

            tv_price = itemView.findViewById(R.id.tv_price);
            tv_location = itemView.findViewById(R.id.tv_location);
        }
    }
}
