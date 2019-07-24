package com.example.dell.projectdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LoginDataActivity extends AppCompatActivity {
    TextView d_name,d_pass;

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_login_data);
        d_name=findViewById(R.id.login_detail_name);
        d_pass=findViewById(R.id.login_detail_pass);
        preferences=getSharedPreferences("login_data",MODE_PRIVATE);

        String nm=preferences.getString("email","");
        String pa=preferences.getString("password","");

        d_name.setText(nm);
        d_pass.setText(pa);
    }
}

