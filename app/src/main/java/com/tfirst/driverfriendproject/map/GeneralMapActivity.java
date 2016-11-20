package com.tfirst.driverfriendproject.map;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tfirst.driverfriendproject.R;
import com.tfirst.driverfriendproject.connections.ConnectionWithServer;

import java.util.Arrays;

public class GeneralMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_FINE_LOCATION = 11;
    private GoogleMap mMap;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ConnectionWithServer connectionWithServer = new ConnectionWithServer(this);
        connectionWithServer.execute("selectmarkers");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (checkPermissions()) {
            setMyLocationEnabled();
        }
    }

    public void addMarkerToMap(String type, Double lat, Double lng, String desc) {
        int icon = 0;
        System.out.println(type);
        if (type.equals("crash")) {
            icon = R.drawable.iconcrash;
        }
        if (type.equals("rps")) {
            icon = R.drawable.iconrps;
        }
        if (type.equals("road_works")) {
            icon = R.drawable.iconroadwork;
        }
        if (type.equals("wheel")) {
            icon = R.drawable.iconwheel;
        }
        if (type.equals("accum")) {
            icon = R.drawable.iconaccum;
        }
        if (type.equals("fuel")) {
            icon = R.drawable.iconfuel;
        }
        if (type.equals("stuck")) {
            icon = R.drawable.iconstuck;
        }

        mMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(icon))
                .position(new LatLng(lat, lng))
                .title(type)
                .snippet(desc)
        );
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setMyLocationEnabled();
                } else {

                }
            }
        }
    }

    public void setResult(String line) {
        String[] parseLocation = line.split("@");
        System.out.println("parselocation length = " + parseLocation.length);
        for (int i = 0; i < parseLocation.length; i++) {
            System.out.println(parseLocation[i]);
            String[] childrenParseLocation = parseLocation[i].split(":");
            System.out.println(Arrays.toString(childrenParseLocation));
            addMarkerToMap(childrenParseLocation[0], Double.parseDouble(childrenParseLocation[1]),
                    Double.parseDouble(childrenParseLocation[2]), childrenParseLocation[3]);
        }
    }

    private void setMyLocationEnabled() {
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}