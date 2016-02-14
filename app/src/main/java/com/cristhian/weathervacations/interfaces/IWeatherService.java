package com.cristhian.weathervacations.interfaces;

import com.cristhian.weathervacations.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cristhian on 2/14/2016.
 */
public interface IWeatherService {

    @GET("weather?")
    Call<Weather> getWeather(
            @Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("appid") String apiKey
    );
}
