package com.example.dell.projectdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.projectdemo.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Create_account extends AppCompatActivity {
    EditText name,email,password,mobile;
    Button createaccount;

    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        name=findViewById(R.id.create_name);
        email=findViewById(R.id.create_email);
        password=findViewById(R.id.create_password);
        mobile=findViewById(R.id.create_mobilenumber);
        createaccount=findViewById(R.id.register);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tname, temail, tpassword, tmobile;
                tname = name.getText().toString();
                temail = email.getText().toString();
                tpassword=password.getText().toString();
                tmobile = mobile.getText().toString();

                if (tname.trim().length() == 0) {
                    name.setError("Don't leave blank");
                }

                if (temail.trim().length() == 0) {
                    email.setError("Don't leave blank");
                }

                if (tpassword.trim().length() == 0) {
                    password.setError("Don't leave blank");
                }



                if (tmobile.trim().length() == 0) {
                    mobile.setError("Don't leave blank");
                }

                Call<User> call = apiInterface.Createaccount(tname,temail,tpassword,tmobile);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user.getResponse().equals("success")) {
                            startActivity(new Intent(Create_account.this,MainActivity.class));

                        }
                        else{
                            Toast.makeText(Create_account.this, "try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure (Call<User> call , Throwable t) {
                        Toast.makeText(Create_account.this,"Error  -"+t, Toast.LENGTH_SHORT).show();
                    }
                });

            }


        });


    }
}
