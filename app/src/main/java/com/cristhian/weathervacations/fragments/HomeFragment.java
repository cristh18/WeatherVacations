package com.cristhian.weathervacations.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cristhian.weathervacations.R;
import com.cristhian.weathervacations.models.Main;

/**
 * Created by Cristhian on 2/13/2016.
 */
public class HomeFragment extends Fragment {

    private final String LOG_TAG = HomeFragment.class.getSimpleName();

    private Main main;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.weather);
        textView.setText("Temp: ".concat(String.valueOf(main.getTemp())));

        return rootView;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
