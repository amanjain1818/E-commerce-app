package com.example.dell.projectdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.projectdemo.pojo.AddCart;
import com.example.dell.projectdemo.pojo.Newarrival;
import com.squareup.picasso.Picasso;

import java.util.List;

class RemoveItemsAdapter extends RecyclerView.Adapter<RemoveItemsAdapter.ViewHolder> {
    RecyclerView cartca;
    Newarrival newarrival;
    Context context;
    List<AddCart> removeList;
    LayoutInflater inflater;
    SharedPreferences preferences;

    public RemoveItemsAdapter(Context context, List<AddCart> removeList) {
        this.context = context;
        this.removeList = removeList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RemoveItemsAdapter.ViewHolder(inflater.inflate(R.layout.removeitems_detail,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(removeList.get(i).getImage()).into(viewHolder.cartimg5);
        viewHolder.name5.setText(removeList.get(i).getName());
        viewHolder.qty5.setText(String.valueOf(removeList.get(i).getQuantity()));
        viewHolder.price5.setText(String.valueOf(removeList.get(i).getPrice()));
        int a=removeList.get(i).getQuantity();
        int b=removeList.get(i).getPrice();
        int c=a*b;
        viewHolder.totalprice5.setText(c+"");
    }

    @Override
    public int getItemCount() {
        return removeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name5,price5,qty5,totalprice5;
        ImageView cartimg5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartimg5=itemView.findViewById(R.id.productImage2);
            name5 = itemView.findViewById(R.id.remove_product_name);
            qty5 = itemView.findViewById(R.id.remove_product_quantity);
            price5 = itemView.findViewById(R.id.remove_product_price);

            totalprice5=itemView.findViewById(R.id.remove_product_total_amount);



        }
    }
}
