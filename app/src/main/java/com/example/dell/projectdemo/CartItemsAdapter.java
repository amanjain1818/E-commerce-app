package com.example.dell.projectdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.projectdemo.pojo.AddCart;
import com.example.dell.projectdemo.pojo.Newarrival;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {
    RecyclerView cartca;
    Newarrival newarrival;
    Context context;
    List<AddCart> cartitemList;
    LayoutInflater inflater;
    SharedPreferences preferences;
    ApiInterface apiInterface;

    public CartItemsAdapter(Context context, List<AddCart> cartitemList) {
        this.context = context;
        this.cartitemList = cartitemList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CartItemsAdapter.ViewHolder(inflater.inflate(R.layout.cardviewitems_detail,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Picasso.with(context).load(cartitemList.get(i).getImage()).into(viewHolder.cartimg);
        viewHolder.name.setText(cartitemList.get(i).getName());
        viewHolder.qty1.setText(String.valueOf(cartitemList.get(i).getQuantity()));
        viewHolder.price1.setText(String.valueOf(cartitemList.get(i).getPrice()));
        int a=cartitemList.get(i).getQuantity();
        int b=cartitemList.get(i).getPrice();
        int c=a*b;
        viewHolder.totalprice.setText(c+"");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "cart items list", Toast.LENGTH_SHORT).show();
                CharSequence options[] = new CharSequence[]
                        {
                                "EDIT",
                                "REMOVE"
                        } ;
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("cart options:");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                          if(i==0)
                          {
                              Intent intent = new Intent(context,OrderPage.class);
                              intent.putExtra("info",newarrival.getId());


                          }
                    }
                });
                builder.show();
            }
        });
     }

    @Override
    public int getItemCount()

    {
        return cartitemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price1,qty1,totalprice;
        ImageView cartimg;
        Button edit,remove;
        //int a,b,c;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartimg=itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.cart_product_name);
            qty1 = itemView.findViewById(R.id.cart_product_quantity);
            totalprice=itemView.findViewById(R.id.cart_product_total_amount);
            //edit=itemView.findViewById(R.id.confromedit);

            remove=itemView.findViewById(R.id.conformremove);
            price1 = itemView.findViewById(R.id.cart_product_price);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences=context.getSharedPreferences("login_data",MODE_PRIVATE);
                    //cartca.setLayoutManager(new LinearLayoutManager(context));
                    apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
                    String email = preferences.getString("email","");

                    Call<AddCart> call = apiInterface.removelist(email,cartitemList.get(getAdapterPosition()).getId());
                    Toast.makeText(context, "id - "+cartitemList.get(getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
                    call.enqueue(new Callback<AddCart>() {
                        @Override
                        public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                            AddCart removeitemlist = response.body();
                            if (removeitemlist.getResponse().equals("success")) {
                                context.startActivity(new Intent(context,CartViewActivity.class));
                                Toast.makeText(context, "  ITEMS  DELETE", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(context, "NO  ITEMS  FOUND", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddCart> call, Throwable t) {
                            Toast.makeText(context, "Error- "+t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
