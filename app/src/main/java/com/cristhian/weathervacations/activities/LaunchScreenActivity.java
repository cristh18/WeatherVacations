package com.cristhian.weathervacations.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cristhian.weathervacations.R;
import com.cristhian.weathervacations.interfaces.IWeatherResponse;
import com.cristhian.weathervacations.network.WeatherTask;
import com.cristhian.weathervacations.utils.Utilies;

/**
 * Created by ctolosa on 02/13/2016.
 */
public class LaunchScreenActivity extends AppCompatActivity implements IWeatherResponse {


    private final String LOG_TAG = LaunchScreenActivity.class.getSimpleName();

    Intent intent;

    public static boolean IS_TABLET = false;

    LocationManager locationManager;
    //    double longitudeBest, latitudeBest;
//    double longitudeGPS, latitudeGPS;
    Double longitudeNetwork, latitudeNetwork;

    String url;
    String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        setContentView(R.layout.activity_launch_screen);
        url = getString(R.string.base_url);
        apiKey = getString(R.string.api_key);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        toggleNetworkUpdates();
    }

    /**
     * @param url
     */
    private void getWeatherPosition(String url) {
        Log.d(LOG_TAG, "Call weatherTask!!!!!!!");
        WeatherTask weatherTask = new WeatherTask(this);
        weatherTask.execute(url, String.valueOf(latitudeNetwork), String.valueOf(longitudeNetwork), apiKey);
    }


    @Override
    public void weatherResponse(Boolean response) {
        if (response) {
//            goToMainActivity();
//            Toast.makeText(this, getResources().getString(R.string.ok), Toast.LENGTH_LONG);
        } else {
//            Toast.makeText(this, getResources().getString(R.string.fail), Toast.LENGTH_LONG);
        }
    }

    /**
     *
     */
    private void goToMainActivity() {
        intent = new Intent(LaunchScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * ----/
     */

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void toggleNetworkUpdates() {
        Log.e(LOG_TAG, "ENTER TO GET GPS POSITION BY NETWORK");
        if (!checkLocation())
            return;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE
            }, 10);
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
        Toast.makeText(this, "Network provider started running", Toast.LENGTH_LONG).show();
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();
            Log.e(LOG_TAG, "LONGITUDE: " + longitudeNetwork + ", LATITUDE: " + latitudeNetwork);
            Toast.makeText(LaunchScreenActivity.this, "Network Provider update", Toast.LENGTH_SHORT).show();
            if (latitudeNetwork != null && longitudeNetwork != null){
                getWeatherPosition(url);
            }

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    //longitudeValueNetwork.setText(longitudeNetwork + "");
//                    //latitudeValueNetwork.setText(latitudeNetwork + "");
//                    Log.e(LOG_TAG, "LONGITUDE: " + longitudeNetwork + ", LATITUDE: " + latitudeNetwork);
//                    Toast.makeText(LaunchScreenActivity.this, "Network Provider update", Toast.LENGTH_SHORT).show();
//                }
//            });
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
}
