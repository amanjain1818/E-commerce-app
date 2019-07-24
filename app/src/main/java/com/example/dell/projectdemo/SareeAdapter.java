package com.example.dell.projectdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appolica.flubber.Flubber;
import com.example.dell.projectdemo.pojo.Newarrival;
import com.squareup.picasso.Picasso;

import java.util.List;


class SareeAdapter extends RecyclerView.Adapter<SareeAdapter.ViewHolder> {
    Context context;
    List<Newarrival> sareeList;
    LayoutInflater inflater;

    public SareeAdapter(Context context, List<Newarrival> sareeList) {
        this.context = context;
        this.sareeList = sareeList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.saree_detain,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(sareeList.get(i).getImage()).into(viewHolder.img2);
        viewHolder.name2.setText(sareeList.get(i).getName());
        viewHolder.price2.setText(sareeList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return sareeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img2;
        TextView name2,price2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name2=itemView.findViewById(R.id.product_name2);
            price2=itemView.findViewById(R.id.product_price2);
            img2=itemView.findViewById(R.id.saree_img);
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Flubber.with()
                            .animation(Flubber.AnimationPreset.SQUEEZE) // Slide up animation
                            .repeatCount(1)                              // Repeat once
                            .duration(1000)                              // Last for 1000 milliseconds(1 second)
                            .createFor(view)                             // Apply it to the view
                            .start();  new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(context,OrderPage.class);
                                    intent.putExtra("info",sareeList.get(getAdapterPosition()));
                                    context.startActivity(intent);
                                }
                            }, 1000);                                   // Start it now
                }
            });



        }
    }
}
