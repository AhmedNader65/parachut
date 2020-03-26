package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrerror.parachut.Models.CategoryModel.Datum;
import com.mrerror.parachut.R;


public class CategoryAdabter extends PagedListAdapter<Datum, CategoryAdabter.CategoryVholder> {
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


    public CategoryAdabter() {
        super(item_COMPARATOR);
    }

    @NonNull
    @Override
    public CategoryVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sections, parent, false);
        return new CategoryVholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVholder holder, int position) {

        Datum item = getItem(position);
        holder.textView.setText(item.getName() + "");

        Glide.with(holder.itemView.getContext()).load(item.getImage()).into(holder.imageView);

    }

    class CategoryVholder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public CategoryVholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtCat);
            imageView = itemView.findViewById(R.id.imgitem_cat);
            cardView = itemView.findViewById(R.id.cardCat);

        }
    }

}
