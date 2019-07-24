package com.example.dell.projectdemo;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

         TextToSpeech tts;
         TextView t1;
         String Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        t1=findViewById(R.id.spalsh);
        tts=new TextToSpeech(SplashActivity.this,SplashActivity.this);
        ShimmerFrameLayout container =findViewById(R.id.shimmer);
        container.startShimmer();
        new Handler().postDelayed(
                new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        }, 1000);

    }

    @Override
    public void onInit(int i) {
        Text=t1.getText().toString();
          tts.setLanguage(Locale.ENGLISH);
          tts.setPitch(1.5f);
          tts.speak(Text,TextToSpeech.QUEUE_FLUSH,null,null);

    }}