package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Models.FinishedOrders.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.home.orders.detailsorder.DetailsOrderActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersAdapter extends PagedListAdapter<Datum , OrdersAdapter.OrdersVmodel> {
    private Context context;
    private static final DiffUtil.ItemCallback<com.mrerror.parachut.Models.FinishedOrders.Datum> item_COMPARATOR = new DiffUtil.ItemCallback<Datum>() {
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

    public OrdersAdapter(Context context) {
        super(item_COMPARATOR);
        this.context = context;

    }

    @NonNull
    @Override
    public OrdersAdapter.OrdersVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allorder, parent, false);
        return new OrdersVmodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersAdapter.OrdersVmodel holder, int position) {
        final Datum item = getItem(position);
        holder.code.setText("#الكود : " + item.getCode());
        holder.status.setText(item.getStatus() + "");
        holder.productNum.setText("عدد المنتجات : " + "(" + item.getOrderProducts().get(0).getQuantity() + ")" + " منتج");
        holder.cost.setText("اجمالي التكلفه :" + item.getFinalPrice() + " جنيه ");
        holder.date.setText(item.getCreatedAt() + "");

        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
        String strDate = sdf.format(date);


        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsOrderActivity.class);
                intent.putExtra("myid" , item.getId());
                context.startActivity(intent);
            }
        });

    }

    public class OrdersVmodel extends RecyclerView.ViewHolder {
        TextView  date , code , productNum , cost , status;
        Button btn_details;
        public OrdersVmodel(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tv_date);
            code = itemView.findViewById(R.id.tv_code);
            productNum = itemView.findViewById(R.id.tv_productsNum);
            cost = itemView.findViewById(R.id.tv_totalCost);
            status = itemView.findViewById(R.id.tv_statuts);
            btn_details = itemView.findViewById(R.id.btn_details);
        }
    }
}
