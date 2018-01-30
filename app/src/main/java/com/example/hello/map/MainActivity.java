package com.example.hello.map;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    map0 map = new map0();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},1);

            //Toast.makeText(this, R.string.error_permission_map, Toast.LENGTH_LONG).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        Bundle arguments = new Bundle();
        FragmentManager fragment = getSupportFragmentManager();


        //String mybus;
        if(bundle != null){
            //mybus = bundle.getString("bus");
            arguments.putInt("TIPO", 6);map.setArguments(arguments);
            fragment.beginTransaction().replace(R.id.contenedor,map).commit();


            //arguments.putInt("TIPO", 6);map.setArguments(arguments);
            //fragment.beginTransaction().replace(R.id.contenedor,map).commit();
        }
        else{
           arguments.putInt("TIPO", 3);map.setArguments(arguments);
           fragment.beginTransaction().replace(R.id.contenedor,map).commit();
        }





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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragment = getSupportFragmentManager();
        //map0 map = new map0();
        Bundle arguments = new Bundle();

        if (id == R.id.normal_map) {
            //Intent myIntent = new Intent(MainActivity.this, map1.class);
            //myIntent.putExtra("name",texto.getText().toString()); //Optional parameters
            //MainActivity.this.startActivity(myIntent);

            map.mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            //arguments.putInt("TIPO", 1);map.setArguments(arguments);
            //fragment.beginTransaction().replace(R.id.contenedor,map).commit();

        } else if (id == R.id.hibrid_map) {

            map.mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            //arguments.putInt("TIPO", 2);map.setArguments(arguments);
            //fragment.beginTransaction().replace(R.id.contenedor,map).commit();

        } else if (id == R.id.mylocation) {
            arguments.putInt("TIPO", 3);map.setArguments(arguments);
            fragment.beginTransaction().replace(R.id.contenedor,map).commit();

        } else if (id == R.id.bus) {
            Intent myIntent = new Intent(MainActivity.this, buses.class);
            //myIntent.putExtra("name",texto.getText().toString()); //Optional parameters
            MainActivity.this.startActivity(myIntent);

        } else if (id == R.id.invite) {
            Toast.makeText(this,"PROXIMAMENTE", Toast.LENGTH_LONG).show();
            //Arguments.putInt("TIPO", 4);map.setArguments(arguments);
            //fragment.beginTransaction().replace(R.id.contenedor,map).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
