package com.example.dell.projectdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.AdapterViewFlipper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.projectdemo.pojo.Newarrival;
import com.example.dell.projectdemo.ui.main.SectionsPagerAdapter;

public class DashBoardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

ImageView imageView;
SharedPreferences preferences;
TextView name,email;
Newarrival newarrival;
Spinner spinner;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        name=findViewById(R.id.nav_title);
        email=findViewById(R.id.nav_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        preferences=getSharedPreferences("login_data",MODE_PRIVATE);
        setSupportActionBar(toolbar);
        // attach to current activity;

        TabLayout tabLayout =(TabLayout) findViewById(R.id.tabs1);
        ViewPager viewPager=(ViewPager) findViewById(R.id.view_pager);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        ImageView imgvw = hView.findViewById(R.id.imageView);
        TextView temail = hView.findViewById(R.id.nav_email);
        TextView tname = hView.findViewById(R.id.nav_title);
//set views
        preferences=getSharedPreferences("login_data",MODE_PRIVATE);
        String email = preferences.getString("email","");
        imgvw.setImageResource(R.drawable.tt);
        temail.setText(email);
        tname.setText("Padmavati Fashion");
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            startActivity(new Intent(DashBoardActivity.this,MainActivity.class));

        } else if (id == R.id.nav_gallery) {

            startActivity(new Intent(DashBoardActivity.this,Image_gallery.class));
        } else if (id == R.id.nav_account) {
            startActivity(new Intent(DashBoardActivity.this,Create_account.class));
            Toast.makeText(this,"create account",Toast.LENGTH_SHORT).show();
        }
         else if (id == R.id.nav_cart) {
            startActivity(new Intent(DashBoardActivity.this,CartViewActivity.class));
        }else if (id == R.id.nav_logout) {
            Toast.makeText(this,"logout",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("status","");
            editor.putString("email","");
            editor.commit();
            startActivity(new Intent(DashBoardActivity.this,MainActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
