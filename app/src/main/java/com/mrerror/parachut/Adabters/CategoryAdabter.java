package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
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
import com.mrerror.parachut.ui.home.allitem.AllItemActivity;


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
    public void onBindViewHolder(@NonNull final CategoryVholder holder, int position) {

        final Datum item = getItem(position);
        holder.textView.setText(item.getName() + "");

        Glide.with(holder.itemView.getContext()).load(item.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), AllItemActivity.class);
                intent.putExtra("key","category");
                intent.putExtra("_id",item.getId()+"");
                Log.e("ZZ1",item.getId()+" ");
                holder.itemView.getContext().startActivity(intent);
            }
        });
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
