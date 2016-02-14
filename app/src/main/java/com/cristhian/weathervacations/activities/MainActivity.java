package com.cristhian.weathervacations.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cristhian.weathervacations.R;
import com.cristhian.weathervacations.fragments.HomeFragment;
import com.cristhian.weathervacations.models.Main;

/**
 * Created by Cristhian on 2/13/2016.
 */
public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Main main;
        Intent intent = getIntent();
        main = intent.getParcelableExtra("weatherDetail");

        if (main != null){
            goToMainScreen(main);
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
}
