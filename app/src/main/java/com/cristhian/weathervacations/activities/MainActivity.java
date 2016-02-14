package com.cristhian.weathervacations.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cristhian.weathervacations.R;
import com.cristhian.weathervacations.fragments.HomeFragment;

/**
 * Created by Cristhian on 2/13/2016.
 */
public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToMainScreen();
    }

    /**
     *
     */
    private void goToMainScreen() {
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_main, homeFragment)
                .commit();
    }
}
