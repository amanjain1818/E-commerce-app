package com.example.dell.projectdemo;

import
        android.content.Context;
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


class LehngaAdapter extends RecyclerView.Adapter<LehngaAdapter.ViewHolder> {
    Context context;
    List<Newarrival> lehngaList;
    LayoutInflater inflater;

    public LehngaAdapter(Context context, List<Newarrival> lehngaList) {
        this.context = context;
        this.lehngaList = lehngaList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.lehnga_detail,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(lehngaList.get(i).getImage()).into(viewHolder.img);
        viewHolder.name.setText(lehngaList.get(i).getName());
        viewHolder.price.setText(lehngaList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return lehngaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_price);
            img=itemView.findViewById(R.id.lehnga_img);
            img.setOnClickListener(new View.OnClickListener() {
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
                                    intent.putExtra("info",lehngaList.get(getAdapterPosition()));
                                    context.startActivity(intent);
                                }
                            }, 1000);                                   // Start it now
                }
            });



        }
    }
}



