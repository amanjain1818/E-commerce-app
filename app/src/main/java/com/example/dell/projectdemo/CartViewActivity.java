package com.example.dell.projectdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.projectdemo.pojo.AddCart;
import com.example.dell.projectdemo.pojo.Newarrival;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewActivity extends AppCompatActivity {
    ImageView cartviewimage;
    TextView c1,c2,c3,cartname,c5,c6;
    Button conformorder , deletecart;
    int a,b,c;
    Spinner spinner;
    Newarrival newarrival;
    RecyclerView cartitems;
    ApiInterface apiInterface;
    TextView quantity1,price1,total_amount;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        cartitems=findViewById(R.id.cartviewlist_item);
        preferences=getSharedPreferences("login_data",MODE_PRIVATE);
        cartitems.setLayoutManager(new LinearLayoutManager(this));
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        String email = preferences.getString("email","");
        Toast.makeText(this, "email this"+email, Toast.LENGTH_SHORT).show();
        Call<List<AddCart>> call = apiInterface.cartlist(email);
        call.enqueue(new Callback<List<AddCart>>() {
                         @Override
                         public void onResponse(Call<List<AddCart>> call, Response<List<AddCart>> response) {
                             List<AddCart> cartitemList = response.body();
                             if(cartitemList.size() > 0){
                                 cartitems.setAdapter(new CartItemsAdapter(CartViewActivity.this,cartitemList));
                             }else{
                                 Toast.makeText(CartViewActivity.this, "NO CART ITEMS RECORD FOUND", Toast.LENGTH_SHORT).show();

                             }
                         }

            @Override
            public void onFailure(Call<List<AddCart>> call, Throwable t) {
                Toast.makeText(CartViewActivity.this, "Error- "+t, Toast.LENGTH_SHORT).show();
            }



                     });
                //c1=findViewById(R.id.cart_Packing);
       // deletecart=findViewById(R.id.delete_cart);
        //quantity1=findViewById(R.id.cart_orderQuantity1);
        //cartname=findViewById(R.id.cart_name);
        //c6=findViewById(R.id.cart_peice1);
        //artviewimage=findViewById(R.id.productImage);
        //conformorder=findViewById(R.id.confrom_order);
      //price1=findViewById(R.id.cart_price1);
     // total_amount=findViewById(R.id.cart_total_amount);

        //Intent intent = getIntent();
        //newarrival = (Newarrival) intent.getSerializableExtra("info");
        //Picasso.with(this).load(newarrival.getImage()).into(cartviewimage);
        //cartname.setText(newarrival.getName());
        //price1.setText(newarrival.getPrice());
        //String order=intent.getStringExtra("qty");
        //quantity1.setText(order);
        //Intent intent = getIntent();
      //  String img = intent.getStringExtra("img");
        //String img1 = intent.getStringExtra("img1");
        //cartviewimage.setImageResource(img);
        //String name= intent.getStringExtra("name1");
       // cartname.setText(name);

        //Picasso.with(this).load(img).into(cartviewimage);
        //String qty=intent.getStringExtra("qty");
        //Toast.makeText(this, "order display"+qty, Toast.LENGTH_SHORT).show();
        //quantity1.setText(qty);
        //String price= intent.getStringExtra("price1");
        //price1.setText(price);


    // a=Integer.parseInt(String.valueOf(quantity1.getText()));
      //  b=Integer.parseInt(String.valueOf(price1.getText()));
        //c=a*b;
        //total_amount.setText(String.valueOf(c));



//        conformorder.setOnClickListener(new View.OnClickListener() {
  //          @Override
    //        public void onClick(View view) {
      //          Intent intent = new Intent(CartViewActivity.this,ConformOrderActivity.class);
                //String order=intent.getStringExtra("data");

                //String img = intent.getStringExtra("img");
                //String img1 = intent.getStringExtra("img1");
                //cartviewimage.setImageResource(img);
                //Picasso.with(CartViewActivity.this).load(img).into(cartviewimage);
                //intent.putExtra("info",newarrival);
                //intent.putExtra("qty", spinner.getSelectedItem().toString());
                //intent.putExtra("img1",lehnga.getImage());
                //intent.putExtra("data",quantity1.getText().toString());
                //startActivity(intent);
            //}
       // });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,OrderPage.class);
        intent.putExtra("info", newarrival);
    }
}
