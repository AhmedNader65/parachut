package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mrerror.parachut.Models.PendingOrders.Datum;
import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.home.orders.detailsorder.DetailsOrderActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PendingOrdersAdapter extends PagedListAdapter<Datum , PendingOrdersAdapter.PendingOrdersVmodel> {
    private Context context;
   private static final DiffUtil.ItemCallback<Datum> item_COMPARATOR = new DiffUtil.ItemCallback<Datum>() {
       @Override
       public boolean areItemsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
           return oldItem.getId() == newItem.getId() ;
       }

       @SuppressLint("DiffUtilEquals")
       @Override
       public boolean areContentsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
           return oldItem == newItem;
       }
   };

    public PendingOrdersAdapter( Context context) {
        super(item_COMPARATOR);
        this.context = context;
    }

    @NonNull
    @Override
    public PendingOrdersAdapter.PendingOrdersVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allorder, parent, false);
        return new PendingOrdersVmodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrdersAdapter.PendingOrdersVmodel holder, int position) {

        final Datum item = getItem(position);
        holder.code.setText("#الكود : " + item.getCode());
        holder.status.setText(item.getStatus() + "");
        holder.productNum.setText("عدد المنتجات : " + "(" + item.getOrderProducts().size() + ")" + " منتج");
        holder.cost.setText("اجمالي التكلفه :" + item.getFinalPrice() + " جنيه ");


//        String time = item.getCreatedAt();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = sdf.parse(time);
//        } catch (ParseException e) {
//            Log.e("22222", e.getMessage());
//        }
//        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//        String formatedTime = sdf2.format(date);
//

   //     holder.date.setText(formatedTime + "");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsOrderActivity.class);
                intent.putExtra("myid", item.getId() + "");
                context.startActivity(intent);
            }
        });
    }

    public class PendingOrdersVmodel extends RecyclerView.ViewHolder {
        TextView date , code , productNum , cost , status;
        Button btn_details;
        public PendingOrdersVmodel(@NonNull View itemView) {
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
