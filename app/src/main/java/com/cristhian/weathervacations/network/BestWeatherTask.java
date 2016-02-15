package com.cristhian.weathervacations.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.cristhian.weathervacations.interfaces.IBestWeatherResponse;
import com.cristhian.weathervacations.interfaces.IWeatherService;
import com.cristhian.weathervacations.models.WeatherData;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Cristhian on 2/14/2016.
 */
public class BestWeatherTask extends AsyncTask<String, Void, WeatherData> {

    private final String LOG_TAG = BestWeatherTask.class.getSimpleName();

    String url;
    String apiKey;
    String units;
    String latitude;
    String longitude;
    String placeName;

    Context context;
    private IBestWeatherResponse iBestWeatherResponse;
    boolean existWeather;

    public BestWeatherTask(Context context, IBestWeatherResponse bestWeatherResponse) {
        this.context = context;
        this.iBestWeatherResponse=bestWeatherResponse;
    }

    @Override
    protected WeatherData doInBackground(String... params) {
        url = params[0];
        latitude = params[1];
        longitude = params[2];
        apiKey = params[3];
        units = params[4];
        placeName = params[5];

        WeatherData weather = getWeather();
        return weather;
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
                weather.getMain().setPlaceName(placeName);
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
            iBestWeatherResponse.weatherResponse(true, weather);
        } else {
            iBestWeatherResponse.weatherResponse(false, null);
        }
    }

}
