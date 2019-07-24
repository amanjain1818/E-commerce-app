package com.example.dell.projectdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.projectdemo.pojo.AddCart;
import com.example.dell.projectdemo.pojo.Newarrival;
import com.example.dell.projectdemo.pojo.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConformOrderActivity extends AppCompatActivity {
    EditText lastname,email,mobile,orderaddress;
    ImageView orderpic;
    Button orderproduct;
    Spinner spinner;
    SharedPreferences preferences;
    Newarrival newarrival;
    TextView t1,t2,ordername,t4,price2,quantity2,totalamount;
    ApiInterface apiInterface;
int a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        orderaddress=findViewById(R.id.order_address);

        t1=findViewById(R.id.cart_peice1);
        t2=findViewById(R.id.cart_Packing);
        ordername=findViewById(R.id.order_name);
        orderpic=findViewById(R.id.productImageOrder);

        totalamount=findViewById(R.id.total_amount);
        orderproduct=findViewById(R.id.order);
        price2=findViewById(R.id.order_price1);
        t4=findViewById(R.id.cart_price);
        quantity2=findViewById(  R.id.cart_orderQuantity1);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

      Intent intent = getIntent();
        newarrival = (Newarrival) intent.getSerializableExtra("info");
        Picasso.with(this).load(newarrival.getImage()).into(orderpic);
        ordername.setText(newarrival.getName());
        price2.setText(newarrival.getPrice());
        String order=intent.getStringExtra("qty");
        quantity2.setText(order);

        //Intent intent = getIntent();
        //String img = intent.getStringExtra("img");

        //String img1 = intent.getStringExtra("img1");
        //cartviewimage.setImageResource(img);
        //Picasso.with(this).load(img).into(cartviewimage);
        //String name2= intent.getStringExtra("name1");
        //ordername.setText(name2);
        //Picasso.with(this).load(img1).into(cartviewimage);
        //String order=intent.getStringExtra("data");
        //quantity2.setText(order);
        //String price= intent.getStringExtra("price1");
        //price2.setText(price);
      //  ordername.setText(newarrival.getName());
        //price2.setText(newarrival.getPrice());

       a=Integer.parseInt(String.valueOf(quantity2.getText()));
        b=Integer.parseInt(String.valueOf(price2.getText()));
        c=a*b;

        totalamount.setText(String.valueOf(c));



        orderproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConformOrderActivity.this, " conform product", Toast.LENGTH_SHORT).show();
                String taddress;
                int tproduct_id;
                int ttotalprice;
                int tquantity;

                ttotalprice = Integer.parseInt(totalamount.getText().toString());
                tproduct_id = newarrival.getId();
                tquantity = Integer.parseInt(quantity2.getText().toString());
                taddress=orderaddress.getText().toString();

                preferences = getSharedPreferences("login_data", MODE_PRIVATE);
                String n = preferences.getString("email", "");
                //Toast.makeText(ConformOrderActivity.this, "email"+email, Toast.LENGTH_SHORT).show();
                Toast.makeText(ConformOrderActivity.this, "email "+n, Toast.LENGTH_SHORT).show();
                Call<AddCart> call = apiInterface.conformord(tproduct_id,ttotalprice,tquantity , n,taddress);
                call.enqueue(new Callback<AddCart>() {

                    @Override
                    public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                        AddCart confo = response.body();
                        if (confo.getResponse().equals("success")) {
                            //startActivity(new Intent(ConformOrderActivity.this,DashBoardActivity.class));
                            Toast.makeText(ConformOrderActivity.this, " Conform Order ", Toast.LENGTH_SHORT).show();
                            orderproduct.setEnabled(false);
                        }
                        else{
                            Toast.makeText(ConformOrderActivity.this, "try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure (Call<AddCart> call , Throwable t) {
                        Toast.makeText(ConformOrderActivity.this,"Error  -"+t, Toast.LENGTH_SHORT).show();
                    }
                });

            }


        });


    }
}
