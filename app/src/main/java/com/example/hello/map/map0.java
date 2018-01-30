package com.example.hello.map;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class map0 extends Fragment{

    private SupportMapFragment mapFragment;

    public GoogleMap mMap;
    public Marker marcador;
    public Marker mybus;
    double lat = 0.0;
    double lng = 0.0;
    int tipo = -1;
    int t = 0;
    boolean update_bus = false;
    ArrayList<LatLng> locations;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        locations = new ArrayList();
        locations.add(new LatLng(-16.4053857, -71.52866210000002));
        locations.add(new LatLng(-16.404614503616354, -71.52785186983647));
        locations.add(new LatLng(-16.404202820046404, -71.52733688570561));
        locations.add(new LatLng(-16.40362646158549, -71.52652149416508));
        locations.add(new LatLng(-16.403008932769055,-71.52583484865727));
        locations.add(new LatLng(-16.40239140199337, -71.52510528780522));
        locations.add(new LatLng(-16.401650362476428,-71.52420406557621));



        return inflater.inflate(R.layout.activity_map1, container, false);



    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            tipo = bundle.getInt("TIPO");
            //int value2 = bundle.getInt("VALUE2", -1);
        }


        FragmentManager fm = getChildFragmentManager();

        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        } else {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    //mMap = googleMap;
                    if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                                    PackageManager.PERMISSION_GRANTED) {
                        googleMap.setMyLocationEnabled(true);

                    }

                    googleMap.getUiSettings().setZoomControlsEnabled(true);

                    if(tipo==1){
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    }
                    else if(tipo==2){
                        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    }
                    else if(tipo==3){
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        miUbicacion(googleMap);
                    }
                    else if(tipo==4){
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    }
                    else if(tipo==5){
                        update_bus = false;
                        //mybus.remove();
                    }

                    else if(tipo==6){

                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                        //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        mybus = googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(-16.4053857, -71.52866210000002))
                                .title("Cotaspa")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon2)));
                        update_bus = true;
                        miUbicacion(googleMap);

                    }

                    //mMap = googleMap;


                    // Add marker in Sydney and move the camera
                    //googleMap.addMarker(new MarkerOptions().position(new LatLng(29.702182, -98.124561)).title("marker"));
                    //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }




                private void agregarMarcador(double lat, double lng) {
                    LatLng coordenadas = new LatLng(lat, lng);
                    CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);

                    if (marcador != null) marcador.remove();
                    marcador = mMap.addMarker(new MarkerOptions()
                            .position(coordenadas)
                            .title("Mi Posicion Actual")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.person)));

                    mMap.animateCamera(miUbicacion);
                }

                private void actualizarUbicacion(Location location) {
                    if (location != null) {
                        lat = location.getLatitude();
                        lng = location.getLongitude();
                        agregarMarcador(lat, lng);

                    }

                    if(update_bus ==true){
                        t = t+1;
                        if(t==locations.size()){
                            t=0;
                        }
                        mybus.setPosition(locations.get(t));
                    }

                }

                LocationListener loclistener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        actualizarUbicacion(location);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                };

                private void miUbicacion(GoogleMap map) {

                    mMap = map;



                    if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                                    PackageManager.PERMISSION_GRANTED) {


                        LocationManager locationM = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
                        if (locationM.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                            Location location = locationM.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            actualizarUbicacion(location);




                            locationM.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,loclistener);





                        }else{
                            Toast.makeText(getContext(), "EL GPS NO ESTA ACTIVADO", Toast.LENGTH_LONG).show();
                        }




                    } else {
                        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);

                        Toast.makeText(getContext(), "NO TIENE PERMISOS DE LOCACION", Toast.LENGTH_LONG).show();
                    }




                }






            });
        }
    }
}
