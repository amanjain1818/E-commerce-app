package com.example.dell.projectdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.projectdemo.pojo.User;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements TextToSpeech.OnInitListener {
    Button login ,createaccount;
    EditText email,password;
    String text;
    SharedPreferences preferences;
    TextToSpeech tts;
    ApiInterface apiInterface;
    String nm,pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        email=findViewById(R.id.login_name);
        createaccount=findViewById(R.id.createaccount);
        password=findViewById(R.id.login_password);

        tts= new TextToSpeech(MainActivity.this,MainActivity.this);
        preferences=getSharedPreferences("login_data",MODE_PRIVATE);


        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Create_account.class));
            }
        });

        String n = preferences.getString("status","");
        if(n.equals("login")){
            startActivity(new Intent(this,DashBoardActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nm=email.getText().toString();
                pa=password.getText().toString();


                if (nm.trim().length() == 0) {
                    email.setError("Don't leave blank");
                }

                if (pa.trim().length() == 0) {
                    password.setError("Don't leave blank");
                }
                Call<User> call = apiInterface.Login(nm,pa);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user.getResponse().equals("success")) {
                            SharedPreferences.Editor editor =preferences.edit();
                            editor.putString("email",nm);
                            editor.putString("status","login");
                            editor.commit();
                            startActivity(new Intent(MainActivity.this,DashBoardActivity.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this, "try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure (Call<User> call , Throwable t) {
                        Toast.makeText(MainActivity.this,"Error  -"+t, Toast.LENGTH_SHORT).show();
                    }
                });

            }


        });
            }



    public void onBackPressed(){
        System.exit(0);
    }

    @Override
    public void onInit(int i) {
        tts.setPitch(1.5f);
        text=login.getText().toString();
                tts.setLanguage(Locale.ENGLISH);
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
    }

}


