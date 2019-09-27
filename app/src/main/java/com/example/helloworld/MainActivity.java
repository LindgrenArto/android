package com.example.helloworld;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MyActivity";

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    ImageButton webViewButton;
    Button testButton;
    TextView testText;
    TextView locationText;
    Button headsOrTails;
    BroadcastReceiver br;
    LocationManager locationManager;
    Location location;
    String currentLocation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewButton = findViewById(R.id.webViewButton);
        testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(this);
        testText = findViewById(R.id.testText);
        locationText = findViewById(R.id.locationTextView);
        headsOrTails = findViewById(R.id.headsOrTailsButton);

        br = new AirPlaneModeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(br, filter);

        locationManager =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                locationText.setText(getAddress(location));
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
        if(locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION );
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);

            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    }

    private String getAddress(Location location) {

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
          Address address = addresses.get(0);
          currentLocation = address.getAddressLine(0);

        } catch (IOException e) {
            Log.e(TAG, "Error" + e);
        }
        Log.i(TAG, currentLocation);
        return currentLocation;
    }


    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }

    public void activateGuess(View view) {
        Intent intent = new Intent(this, GuessActivity.class);
        startActivity(intent);
    }
    public void activateWebPage(View view) {
        Uri webPage = Uri.parse("https://google.com/maps?q="+ currentLocation + "&output-embed");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }

    public void setTextViewVisibility() {
        testText.setVisibility(testText.getVisibility() == View.VISIBLE ? View.GONE: View.VISIBLE);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.testButton:
                Log.i(TAG, "working");
                setTextViewVisibility();
                break;

            case R.id.headsOrTailsButton:
                activateGuess(view);
                break;

            case R.id.webViewButton:
                activateWebPage(view);
                break;
        }
    }
}
