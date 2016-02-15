package com.cristhian.weathervacations.interfaces;

import com.cristhian.weathervacations.models.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cristhian on 2/14/2016.
 */
public interface IWeatherService {

    @GET("weather?")
    Call<WeatherData> getWeather(
            @Query("units") String units,
            @Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("appid") String apiKey
    );
}
