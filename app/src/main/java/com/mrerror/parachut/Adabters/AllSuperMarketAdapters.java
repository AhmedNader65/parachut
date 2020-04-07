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
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrerror.parachut.Models.AllSuperMarket.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.home.allitem.AllItemActivity;

public class AllSuperMarketAdapters extends PagedListAdapter<Datum, AllSuperMarketAdapters.SuperMarketVholder> {

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

    public AllSuperMarketAdapters() {
        super(item_COMPARATOR);
    }

    @NonNull
    @Override
    public SuperMarketVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supermarket,parent,false);
        return new SuperMarketVholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final SuperMarketVholder holder, int position) {

        Log.e("0000kk",position+"");
        final Datum item = getItem(position);
        holder.textViewcount.setText(item.getMinimam()+" ");
        holder.textView.setText(item.getName()+" ");
        Glide.with(holder.itemView.getContext()).load(item.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), AllItemActivity.class);
                intent.putExtra("key","ProductStores");
                intent.putExtra("_id",item.getId()+"");
                Log.e("ZZ2",item.getId()+"");
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    class SuperMarketVholder extends RecyclerView.ViewHolder {
        TextView textViewcount;
        ImageView imageView;
        TextView textView;
        public SuperMarketVholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.namesm);
            imageView = itemView.findViewById(R.id.imgsm);
            textViewcount = itemView.findViewById(R.id.prouctsCountsm);
        }
    }
}
