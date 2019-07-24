package com.example.dell.projectdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.projectdemo.pojo.AddCart;
import com.example.dell.projectdemo.pojo.Newarrival;
//import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import javax.sql.CommonDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button addcart, buynow;
    SharedPreferences preferences;
    ImageView mainImage, mic;
    private final int REQ_CODE = 100;
    String nm;
   // NotificationBadge badge;
    Newarrival newarrival;
    private int count = 0;
    ApiInterface apiInterface;
    TextView name, product_name, colour, color_product, price, price1, quantity;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        product_name = findViewById(R.id.productname);

        name = findViewById(R.id.productname1);
        colour = findViewById(R.id.productcolor);
        color_product = findViewById(R.id.productcolor1);
        price1 = findViewById(R.id.productprice);
        price = findViewById(R.id.productprice1);
        quantity = findViewById(R.id.productquantity);
        addcart = findViewById(R.id.productcart);
        buynow = findViewById(R.id.productbuy);
        mainImage = findViewById(R.id.mainImage);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Intent intent = getIntent();
        newarrival = (Newarrival) intent.getSerializableExtra("info");
        Picasso.with(this).load(newarrival.getImage()).into(mainImage);
        name.setText(newarrival.getName());
        price.setText(newarrival.getPrice());

//        Picasso.with(this).load(lehnga.getImage()).into(mainImage);

        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(OrderPage.this, " In Cart", Toast.LENGTH_SHORT).show();
                int tproduct_id;
                int tprice;
                int tquantity;


                //  tprice = price1.getText().toString();
                tprice = Integer.parseInt(price.getText().toString());
                tproduct_id = newarrival.getId();
                tquantity = Integer.parseInt(spinner.getSelectedItem().toString());

                preferences = getSharedPreferences("login_data", MODE_PRIVATE);
                String n = preferences.getString("email", "");
                Call<AddCart> call = apiInterface.addToCart(tproduct_id, tprice, tquantity, n);
                call.enqueue(new Callback<AddCart>() {

                    @Override
                    public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                        AddCart cart = response.body();
                        if (cart.getResponse().equals("success")) {
                            //startActivity(new Intent(OrderPage.this, OrderPage.class));
                            Toast.makeText(OrderPage.this, "Product Add in Cart", Toast.LENGTH_SHORT).show();
                            addcart.setEnabled(false);
                        } else {
                            Toast.makeText(OrderPage.this, "try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddCart> call, Throwable t) {
                        Toast.makeText(OrderPage.this, "Error  -" + t, Toast.LENGTH_SHORT).show();

                    }

                });


                //Intent intent = new Intent();
                // intent.putExtra("img", newarrival.getImage());
                //intent.putExtra("img1",lehnga.getImage());
                // intent.putExtra("name1",newarrival.getName());
                //intent.putExtra("price1",newariterival.getPrice());
                //Toast.makeText(OrderPage.this, "Item -"+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
               // intent.putExtra("data",spinner.getSelectedItem().toString());
                // startActivity(intent);
            }
        });

        buynow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(OrderPage.this, " buy now", Toast.LENGTH_SHORT).show();
                int tproduct_id;
                int tprice;
                int tquantity;


                //  tprice = price1.getText().toString();
                tprice = Integer.parseInt(price.getText().toString());
                tproduct_id = newarrival.getId();
                tquantity = Integer.parseInt(spinner.getSelectedItem().toString());

                preferences = getSharedPreferences("login_data", MODE_PRIVATE);
                String n = preferences.getString("email", "");
                Call<AddCart> call = apiInterface.conform(tproduct_id, tprice, tquantity, n);
                call.enqueue(new Callback<AddCart>() {

                    @Override
                    public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                        AddCart con = response.body();
                        if (con.getResponse().equals("success")) {
                            Intent intent = new Intent(OrderPage.this, ConformOrderActivity.class);
                            intent.putExtra("info",newarrival);
                            intent.putExtra("qty",spinner.getSelectedItem().toString());
                            startActivity(intent);
                            Toast.makeText(OrderPage.this, "Product Add in buy", Toast.LENGTH_SHORT).show();
                            buynow.setEnabled(false);
                        } else {
                            Toast.makeText(OrderPage.this, "try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddCart> call, Throwable t) {
                        Toast.makeText(OrderPage.this, "Error  -" + t, Toast.LENGTH_SHORT).show();

                    }

                });



            //   Intent intent = new Intent(OrderPage.this, ConformOrderActivity.class);
                //intent.putExtra("img", newarrival.getImage());
                //intent.putExtra("name1", newarrival.getName());
                //intent.putExtra("price1", newarrival.getPrice());
              //  intent.putExtra("info",newarrival);
                //intent.putExtra("qty", spinner.getSelectedItem().toString());
              //  startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        //View view=menu.findItem(R.id.cart_image3).getActionView();
      // badge= (NotificationBadge) findViewById(R.id.badge);

       // view.setOnClickListener(
              //  new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
            //    badge.setNumber(++count);
          //  }
        //});
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.cart_image3)
        {
          Toast.makeText(this,"cart",Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(OrderPage.this,CartViewActivity.class);
           // intent.putExtra("img", newarrival.getImage());
           // intent.putExtra("name1",newarrival.getName());
            //intent.putExtra("price1",newarrival.getPrice());
            intent.putExtra("info", newarrival);
            intent.putExtra("qty", spinner.getSelectedItem().toString());

            startActivity(intent);



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text= adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT);

      //  Intent intent2= new Intent(OrderPage.this,CartViewActivity.class);
        //intent2.putExtra("data","order");
        //startActivity(intent2);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    }


      /*mic.setOnClickListener(new View.OnClickListener() {
        @Override
          public void onClick(View view) {
              Intent intent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
              intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
              intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
              intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Order");
              try{
                  startActivityForResult(intent,REQ_CODE);
              }catch(ActivityNotFoundException e){
                  e.printStackTrace();
              }
          }
      });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      if(resultCode == RESULT_OK && data != null) {
            ArrayList<String> res =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            quantity1.setText(res.get(0));

        }
        }
    }*/

