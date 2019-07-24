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

class NewarrivalAdapter extends RecyclerView.Adapter<NewarrivalAdapter.ViewHolder> {

    Context context;
    List<Newarrival> newarrivalList;
    LayoutInflater inflater;

    public NewarrivalAdapter(Context context, List<Newarrival> newarrivalList) {
        this.context = context;
        this.newarrivalList = newarrivalList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.newarrival_detail,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(newarrivalList.get(i).getImage()).into(viewHolder.img1);
        viewHolder.name1.setText(newarrivalList.get(i).getName());
        viewHolder.price1.setText(newarrivalList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return newarrivalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView name1,price1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name1=itemView.findViewById(R.id.product_name1);
            price1=itemView.findViewById(R.id.product_price1);
            img1=itemView.findViewById(R.id.newarrival_img);
            img1.setOnClickListener(new View.OnClickListener() {
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
                                    intent.putExtra("info",newarrivalList.get(getAdapterPosition()));
                                    context.startActivity(intent);
                                }
                            }, 1000);                                   // Start it now
                }
            });



        }
    }
}
