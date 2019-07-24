package com.example.dell.projectdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Currency;

public class Image_gallery extends AppCompatActivity {
  ImageView galleryimage;
  private static final  int PERMISSION_REQUEST=0;
    private static final  int RESULT_LOAD_IMAGE=1;
    Button imagebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        galleryimage=findViewById(R.id.imagegallery);
        imagebutton=findViewById(R.id.imagebutton);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST);
        }
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }


        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
        case RESULT_LOAD_IMAGE:
            if(resultCode == RESULT_OK){
                Uri selectedimage= data.getData();
                String[] feildpathcolumn={MediaStore.Images.Media.DATA};
                Cursor cursor= getContentResolver().query(selectedimage,feildpathcolumn,null,null,null);
                cursor.moveToFirst();
                int columindex=cursor.getColumnIndex(feildpathcolumn[0]);
                String picturepath= cursor.getString(columindex);
                cursor.close();
                galleryimage.setImageBitmap(BitmapFactory.decodeFile(picturepath));
            }
        }
    }
}

