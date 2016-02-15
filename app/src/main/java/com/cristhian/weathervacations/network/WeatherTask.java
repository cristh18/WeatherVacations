package com.cristhian.weathervacations.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.cristhian.weathervacations.interfaces.IWeatherResponse;
import com.cristhian.weathervacations.interfaces.IWeatherService;
import com.cristhian.weathervacations.models.WeatherData;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Cristhian on 2/14/2016.
 */
public class WeatherTask extends AsyncTask<String, Void, WeatherData> {

    private final String LOG_TAG = WeatherTask.class.getSimpleName();

    private IWeatherResponse iWeatherResponse;

    private static final int SPLASH_TIME = 3000;

    String url;
    String apiKey;
    String units;
    String latitude;
    String longitude;

    boolean existWeather;

    Context context;

    public WeatherTask(Context context, IWeatherResponse weatherResponse) {
        this.context = context;
        this.iWeatherResponse=weatherResponse;
    }

    @Override
    protected WeatherData doInBackground(String... params) {
        url = params[0];
        latitude = params[1];
        longitude = params[2];
        apiKey = params[3];
        units = params[4];
        try {
            Thread.sleep(SPLASH_TIME);
            WeatherData weather = getWeather();
            return weather;
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, e.getMessage());
            return null;
        }
    }

    /**
     * Get apps catalog
     *
     * @return
     */
    private WeatherData getWeather() {
        WeatherData weather = null;
        existWeather = false;
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            IWeatherService iWeatherService = retrofit.create(IWeatherService.class);
            Call<WeatherData> call = iWeatherService.getWeather(units, latitude, longitude, apiKey);
            Response<WeatherData> response = call.execute();
            Log.e("LOG", "Retrofit Response: " + response.raw().toString());

            if (response.body() != null) {
                weather = response.body();
                existWeather = true;
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            Log.e(LOG_TAG, e.toString());
        }


        return weather;
    }

    @Override
    protected void onPostExecute(WeatherData weather) {
        if (weather != null && existWeather) {
            iWeatherResponse.weatherResponse(true, weather);
        } else {
            iWeatherResponse.weatherResponse(false, null);
        }
    }
}
