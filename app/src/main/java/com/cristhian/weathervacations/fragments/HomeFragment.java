package com.cristhian.weathervacations.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cristhian.weathervacations.R;
import com.cristhian.weathervacations.activities.LaunchScreenActivity;
import com.cristhian.weathervacations.interfaces.IWeatherResponse;
import com.cristhian.weathervacations.models.Main;
import com.cristhian.weathervacations.models.Place;
import com.cristhian.weathervacations.models.Weather;
import com.cristhian.weathervacations.network.WeatherTask;
import com.cristhian.weathervacations.utils.Utilies;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Cristhian on 2/13/2016.
 */
public class HomeFragment extends Fragment implements IWeatherResponse {

    private final String LOG_TAG = HomeFragment.class.getSimpleName();

    private Main main;

    GoogleMap mMap;

    EditText searchField;
    Button searchButton;
    TextView textView;

    EditText searchField2;
    Button searchButton2;

    boolean firstLocation;
    boolean secondLocation;
    TextView textView2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initMap();
        goToLocation(main.getLat(), main.getLon(), 15);

        searchField = (EditText) rootView.findViewById(R.id.editText1);
        searchButton = (Button) rootView.findViewById(R.id.button1);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    firstLocation = true;
                    secondLocation = false;
                    geoLocate(v);
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.toString());
                }
            }
        });

        searchField2 = (EditText) rootView.findViewById(R.id.editText2);
        searchButton2 = (Button) rootView.findViewById(R.id.button2);
        searchButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    firstLocation = false;
                    secondLocation = true;
                    geoLocate(v);
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.toString());
                }
            }
        });

        textView = (TextView) rootView.findViewById(R.id.weather);
        textView.setText("Temp: ".concat(Utilies.formatTemperature(getActivity(), main.getTemp())));
        //textView.setText("Temp: ".concat(String.valueOf(main.getTemp())));

        textView2 = (TextView) rootView.findViewById(R.id.weather2);
        textView2.setVisibility(View.GONE);

        return rootView;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * @return
     */
    private boolean initMap() {
        if (mMap == null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
        }
        return (mMap != null);
    }

    /**
     * @param lat
     * @param lng
     */
    private void goToLocation(double lat, double lng, float zoom) {
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);
    }

    /**
     * @param v
     */
    private void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * @param v
     */
    public void geoLocate(View v) throws IOException {
        hideSoftKeyboard(v);

        EditText editText = null;
        String searchString = null;
        if (firstLocation){
            editText = (EditText) getActivity().findViewById(R.id.editText1);
        }else if (secondLocation){
            editText = (EditText) getActivity().findViewById(R.id.editText2);
        }
        searchString = editText.getText().toString();

        Geocoder gc = new Geocoder(getActivity());
        List<Address> list = gc.getFromLocationName(searchString, 1);

        if (list.size() > 0 && !list.isEmpty()) {
            Address add = list.get(0);
            String locality = add.getLocality();
            Toast.makeText(getActivity(), "Found: " + locality, Toast.LENGTH_SHORT).show();
            double latitude = add.getLatitude();
            double longitude = add.getLongitude();
            Log.e(this.getClass().getName(), "Locality: " + locality + " -- Latitude=" + latitude + ", Longitude=" + longitude);
            goToLocation(latitude, longitude, 15);
            /**---*/
            Place place = new Place();
            place.setName(locality);
            place.setLatitude(latitude);
            place.setLongitude(longitude);
            getPlaceWeather(place);
        }
    }

    /**
     * @param place
     */
    private void getPlaceWeather(Place place) {
        getWeather(place);
    }

    /**
     * @param place
     */
    private void getWeather(Place place) {
        Log.d(LOG_TAG, "Call weatherTask!!!!!!!");
        String url = LaunchScreenActivity.url;
        String apiKey = LaunchScreenActivity.apiKey;
        String units = LaunchScreenActivity.units;
        WeatherTask weatherTask = new WeatherTask(getActivity(), this);
        weatherTask.execute(url, String.valueOf(place.getLatitude()), String.valueOf(place.getLongitude()), apiKey, units);
    }

    @Override
    public void weatherResponse(Boolean response, Weather weather) {
        if (response) {
            Log.e(LOG_TAG, "There are response from selected site");
            if (firstLocation) {
                textView.setText("Temp in selected place is: " + Utilies.formatTemperature(getActivity(), weather.getMain().getTemp()));
            } else if (secondLocation) {
                textView2.setVisibility(View.VISIBLE);
                textView2.setText("Temp in selected place is: " + Utilies.formatTemperature(getActivity(), weather.getMain().getTemp()));
            }

        }
    }
}
