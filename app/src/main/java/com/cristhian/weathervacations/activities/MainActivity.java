package com.cristhian.weathervacations.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cristhian.weathervacations.R;
import com.cristhian.weathervacations.fragments.HomeFragment;
import com.cristhian.weathervacations.models.Main;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by Cristhian on 2/13/2016.
 */
public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Main main;
        Intent intent = getIntent();
        main = intent.getParcelableExtra("weatherDetail");

        if (servicesOK()) {
            if (main != null) {
                goToMainScreen(main);
            }
        } else {
            Toast.makeText(this, "Map not connected!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     */
    private void goToMainScreen(Main main) {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setMain(main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_main, homeFragment)
                .commit();
    }


    /**
     * @return
     */
    public boolean servicesOK() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Can´t connect to mapping services", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
