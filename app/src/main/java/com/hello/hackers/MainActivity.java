package com.hello.hackers;


import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.hello.hackers.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , FragmentReminder.OnFragmentInteractionListener ,
        Knowmed.OnFragmentInteractionListener,AboutUs.OnFragmentInteractionListener
        ,HealthTips.OnFragmentInteractionListener,Home.OnFragmentInteractionListener
         {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageView iv;
    TextView tv;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // name should be changed!
            setTaskDescription( new ActivityManager.TaskDescription("hello hackers!!",bitmap));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //name should be changed!
        getSupportActionBar().setTitle("Hello hackers!!");
//        getSupportActionBar().setIcon(R.drawable.single_line_logo);
        LinearLayout layout=findViewById(R.id.container);





        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        Intent intent = getIntent();
        if(intent.getExtras()!=null)
        {
            if(intent.getExtras().getInt("id")!=0)
            {
                int i = intent.getExtras().getInt("id");
                String s=intent.getExtras().getString("text");
                View headerView =navigationView.inflateHeaderView(R.layout.nav_header_main);

                iv=(ImageView)headerView.findViewById(R.id.nav_imageView) ;
                tv=(TextView) headerView.findViewById(R.id.nav_tv);
                tv.setText("Hi, "+s);
                iv.setImageResource(i);

                SharedPreferences sp=getSharedPreferences("Shpr", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putBoolean("logged",true);
                ed.putString("User",s);
                ed.putInt("imgRes",i);
                ed.commit();
                Home hm = new Home();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, hm).commit();
            }
            else {
                FragmentReminder rm = new FragmentReminder();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, rm).commit();
                SharedPreferences sp=getSharedPreferences("Shpr", Context.MODE_PRIVATE);
                int i = sp.getInt("imgRes",0);
                String s=sp.getString("User",null);
                View headerView =navigationView.inflateHeaderView(R.layout.nav_header_main);

                iv=(ImageView)headerView.findViewById(R.id.nav_imageView) ;
                tv=(TextView) headerView.findViewById(R.id.nav_tv);
                tv.setText("Hi, "+s);
                iv.setImageResource(i);
            }

        }
        else {
            Home hm = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, hm).commit();

            SharedPreferences sp=getSharedPreferences("Shpr", Context.MODE_PRIVATE);
            Boolean loggedin=sp.getBoolean("logged",false);

            if(!loggedin)
            {
                CustomDialogClass cdd = new CustomDialogClass(this, getApplicationContext());
                cdd.show();
            }
            else
            {
                int i = sp.getInt("imgRes",0);
                String s=sp.getString("User",null);
                View headerView =navigationView.inflateHeaderView(R.layout.nav_header_main);

                iv=(ImageView)headerView.findViewById(R.id.nav_imageView) ;
                tv=(TextView) headerView.findViewById(R.id.nav_tv);
                tv.setText("Hi, "+s);
                iv.setImageResource(i);
            }
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

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
        getMenuInflater().inflate(R.menu.main, menu);
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
            AboutUs aboutUs=new AboutUs();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,aboutUs).addToBackStack(null).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Log.d("Item:",item.toString());
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_medrem) {
            FragmentReminder rm=new FragmentReminder();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,rm).commit();
            getSupportActionBar().setTitle("Med Reminder");

            // Handle the camera action

        } else if (id == R.id.nav_medinfo) {
            Knowmed km=new Knowmed();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,km).commit();
            getSupportActionBar().setTitle("Know Your Meds");


        } else if (id == R.id.nav_abtus) {
            AboutUs au=new AboutUs();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,au).commit();
            getSupportActionBar().setTitle("About Us");


        }
        else if (id == R.id.nav_chgname) {
            SharedPreferences sp=getSharedPreferences("Shpr", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed=sp.edit();
            ed.putBoolean("logged",false);
            ed.putString("User",null);
            ed.putInt("imgRes",0);
            ed.commit();
            CustomDialogClass cdd = new CustomDialogClass(this, getApplicationContext());
            cdd.show();
        }
        else if (id == R.id.nav_healthtips) {
            HealthTips ht=new HealthTips();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,ht).commit();
            getSupportActionBar().setTitle("Know Your meds");


        }
        else if(id==R.id.gottohome)
        {
            Home hm = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, hm).commit();
            //name should be changed!
            getSupportActionBar().setTitle("Hello hacker!!");

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
             @Override
             protected void onResume() {
                 super.onResume();
                 MyApplication.activityResumed();
             }

             @Override
             protected void onPause() {
                 super.onPause();
                 MyApplication.activityPaused();
             }

}
