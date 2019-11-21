package com.example.helloworld.MainPackage;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.helloworld.AirPlanePackage.AirPlaneModeReceiver;
import com.example.helloworld.AlarmClockPackage.AlarmClockActivity;
import com.example.helloworld.ContactsPackage.ContactsActivity;
import com.example.helloworld.GuessPackage.GuessActivity;
import com.example.helloworld.R;
import com.example.helloworld.TimerPackage.TimerActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MyActivity";

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private DrawerLayout drawer;

    ImageButton webViewButton;
    Button testButton;
    TextView testText, locationText;
    BroadcastReceiver br;
    LocationManager locationManager;
    Location location;
    String currentLocation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigation = findViewById(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        webViewButton = findViewById(R.id.webView_action);
        webViewButton.setOnClickListener(this);
        testButton = findViewById(R.id.test_action);
        testButton.setOnClickListener(this);
        testText = findViewById(R.id.testText);
        locationText = findViewById(R.id.locationTextView);

        br = new AirPlaneModeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(br, filter);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
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
        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 50000, 0, locationListener);

            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_alarm:
                Intent intent = new Intent(MainActivity.this, AlarmClockActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_guess:
                intent = new Intent(MainActivity.this, GuessActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_timer:
                intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
                break;

                case R.id.nav_contacts:
                intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(intent);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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


    public void activateWebPage(View view) {
        Uri webPage = Uri.parse("https://google.com/maps?q=" + currentLocation + "&output-embed");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }

    public void setTextViewVisibility() {
        testText.setVisibility(testText.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_action:
                Log.i(TAG, "working");
                setTextViewVisibility();
                break;

            case R.id.webView_action:
                activateWebPage(view);
                break;

        }
    }
}
